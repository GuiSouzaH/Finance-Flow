package financeflow.service;

import financeflow.dto.AlertasResponseDTO;
import financeflow.dto.CategoriaGastoDTO;
import financeflow.dto.CategoriasResponseDTO;
import financeflow.dto.MensalResponseDTO;
import financeflow.enums.CategoriaTransacao;
import financeflow.enums.TipoTransacao;
import financeflow.model.entity.TransacaoEntity;
import financeflow.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TransacaoRepository transacaoRepository;



    public CategoriasResponseDTO calcularPorCategoria (UUID usuarioId) {


        List<TransacaoEntity> lista = transacaoRepository.findByUsuarioIdAndDataTransacaoBetween
                (usuarioId,YearMonth.now().atDay(1), YearMonth.now().atEndOfMonth());

        //nessa parte estamos pegando por despesa, filtrando transacoes por tipo
        // e por fim somando os valores com reducing
        // depois transforma em lista novamente

                List<CategoriaGastoDTO> listaFinal =    lista.stream()
                    .filter(t -> t.getTipoTransacao() == TipoTransacao.DESPESA)
                    .collect(Collectors.groupingBy(TransacaoEntity::getCategoriaTransacao,
                            Collectors.reducing (BigDecimal.ZERO, TransacaoEntity::getValor, BigDecimal::add)))
                        .entrySet()
                        .stream()
                        .map(entry -> new CategoriaGastoDTO(entry.getKey(), entry.getValue()))
                        .collect(toList());


        return new CategoriasResponseDTO(listaFinal);

    }

    public MensalResponseDTO calcularMensal (UUID usuarioId) {



        LocalDate periodoReferencia = YearMonth.now().atDay(1);

       List<TransacaoEntity> lista = transacaoRepository.findByUsuarioIdAndDataTransacaoBetween
               (usuarioId,YearMonth.now().atDay(1), YearMonth.now().atEndOfMonth());

        BigDecimal receitas = lista.stream()
                .filter(t -> t.getTipoTransacao() == TipoTransacao.RECEITA)
                .map(TransacaoEntity::getValor)
                // Extrai apenas os valores e soma usando reduce, iniciando com BigDecimal.ZERO
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal despesas = lista.stream()
                .filter(t -> t.getTipoTransacao() == TipoTransacao.DESPESA)
                .map(TransacaoEntity::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldo = receitas.subtract(despesas);

        return new MensalResponseDTO(periodoReferencia, receitas, despesas, saldo);

    }

    public AlertasResponseDTO gerarAlertas (UUID usuarioId){

        //Gero uma lista de alertas
        List<String> alertas = new ArrayList<>();

        MensalResponseDTO mensal = calcularMensal(usuarioId);

        //Busca por usuario entre certo periodo
        List<TransacaoEntity> lista = transacaoRepository.findByUsuarioIdAndDataTransacaoBetween
                (usuarioId,YearMonth.now().atDay(1), YearMonth.now().atEndOfMonth());

        //Busca por usuario do mes anterior
        List<TransacaoEntity> listaMesAnterior = transacaoRepository.findByUsuarioIdAndDataTransacaoBetween
                (usuarioId,YearMonth.now().minusMonths(1).atDay(1),
                        YearMonth.now().minusMonths(1).atEndOfMonth());

        //mesmo codigo das receitas
        BigDecimal receitas = mensal.totalReceitas();

        //mesmo codigo das despesas
        BigDecimal despesas = mensal.totalDespesas();

        //mesmo codigo das despesas do mes anterior, utilizando o stream na listaMesAnterior
        BigDecimal despesasMesAnterior = listaMesAnterior.stream()
                .filter(t -> t.getTipoTransacao() == TipoTransacao.DESPESA)
                .map(TransacaoEntity::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        //saldo
        BigDecimal saldo = mensal.saldo();

        //agrupa despesas por categoria e encontra a de maior valor
        Optional<Map.Entry<CategoriaTransacao, BigDecimal>> categoriaComMaiorGasto = lista.stream()
                .filter(t -> t.getTipoTransacao() == TipoTransacao.DESPESA)
                .collect(Collectors.groupingBy(TransacaoEntity::getCategoriaTransacao,
                        Collectors.reducing(BigDecimal.ZERO, TransacaoEntity::getValor, BigDecimal::add)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());


        // Extrai a categoria com maior gasto do Optional
        CategoriaTransacao topCategoria = categoriaComMaiorGasto.map(Map.Entry::getKey).orElse(null);

        if (saldo.compareTo(BigDecimal.ZERO) < 0) {
            alertas.add("Atenção: Seu saldo está negativo este mês!");
        }
        if (despesas.compareTo(receitas) > 0) {
            alertas.add("Atenção: Suas despesas estão maiores que suas receitas este mês!");
        }
        if (categoriaComMaiorGasto.isPresent()) {
            alertas.add("Atenção: Nesse mês sua categoria com maior gasto foi -> " + topCategoria);
        }
        if (despesas.compareTo(despesasMesAnterior) > 0) {
            alertas.add("Atenção: Suas despesas aumentaram em relação ao mês anterior!");
        }

        return new AlertasResponseDTO(alertas);
    }

}
