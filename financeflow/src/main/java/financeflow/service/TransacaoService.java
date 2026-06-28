package financeflow.service;


import financeflow.dto.SaldoResponseDTO;
import financeflow.dto.TransacaoRequestDTO;

import financeflow.dto.TransacaoResponseDTO;
import financeflow.enums.TipoTransacao;
import financeflow.exception.TransacaoNaoEncontradaException;
import financeflow.exception.UsuarioNaoEncontradoException;
import financeflow.model.TransacaoEntity;
import financeflow.model.UsuarioEntity;
import financeflow.repository.TransacaoRepository;
import financeflow.repository.TransacaoRepositoryPageble;
import financeflow.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TransacaoRepositoryPageble transacaoRepositoryPageble;


    //pega os dados da entity e passa para o responseDTO
    private TransacaoResponseDTO toRespondeDto (TransacaoEntity entity) {
        return new TransacaoResponseDTO(
                entity.getId(),
                entity.getDescricao(),
                entity.getValor(),
                entity.getDataTransacao(),
                entity.getTipoTransacao(),
                entity.getCategoriaTransacao(),
                entity.getDataCriacao()
        );
    }



    public TransacaoResponseDTO criarTransacao (UUID usuarioId, TransacaoRequestDTO transacaoRequestDTO)  {


        //busca se o usuario já existe, porque se não existe, como criar a transacao???
       UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
               .orElseThrow(() -> new UsuarioNaoEncontradoException("Esse usuário não foi encontrado"));

        //após passar pela verificacao, cria a transacao para o usuario com os dados que ele passar, nao pede id pois gera automatico
        // nem data de criacao
       TransacaoEntity transacao = TransacaoEntity.builder()
               .descricao(transacaoRequestDTO.descricao())
               .valor(transacaoRequestDTO.valor())
               .dataTransacao(transacaoRequestDTO.dataTransacao())
               .tipoTransacao(transacaoRequestDTO.tipoTransacao())
               .categoriaTransacao(transacaoRequestDTO.categoriaTransacao())
               .usuario(usuario)
               .build();

       TransacaoEntity transacaoSalva = transacaoRepository.save(transacao);
       return toRespondeDto(transacaoSalva);

    }

    //espera retorno de uma lista
    public Page<TransacaoResponseDTO> listarTransacoes (UUID usuarioId , Pageable pageable) {
        return transacaoRepository.findByUsuarioId( usuarioId, pageable).map( e -> new TransacaoResponseDTO(
                e.getId(),
                e.getDescricao(),
                e.getValor(),
                e.getDataTransacao(),
                e.getTipoTransacao(),
                e.getCategoriaTransacao(),
                e.getDataCriacao()
        ));

    }
    public TransacaoResponseDTO buscarPorId(UUID usuarioId, UUID transacaoId) {

        TransacaoEntity transacao = transacaoRepository.findByIdAndUsuarioId(transacaoId,usuarioId)
                .orElseThrow(() -> new TransacaoNaoEncontradaException("Transação não encontrada"));

        return toRespondeDto(transacao);

    }

    @Transactional
    public TransacaoResponseDTO atualizarTransacao(UUID usuarioId, UUID transacaoId, TransacaoRequestDTO transacaoRequestDTO) {

        TransacaoEntity transacaoExistente = transacaoRepository.findByIdAndUsuarioId(transacaoId, usuarioId)
                .orElseThrow(() -> new TransacaoNaoEncontradaException("Transação não encontrada"));

        transacaoExistente.setDescricao(transacaoRequestDTO.descricao());
        transacaoExistente.setValor(transacaoRequestDTO.valor());
        transacaoExistente.setDataTransacao(transacaoRequestDTO.dataTransacao());
        transacaoExistente.setTipoTransacao(transacaoRequestDTO.tipoTransacao());
        transacaoExistente.setCategoriaTransacao(transacaoRequestDTO.categoriaTransacao());

      TransacaoEntity novaTransacao =  transacaoRepository.save(transacaoExistente);

      return toRespondeDto(novaTransacao);
    }

    //busca todas transacoes por usuario e id e deleta
    public void deletarTransacao (UUID usuarioID, UUID transacaoId) {


        TransacaoEntity buscarTransacao = transacaoRepository.findByIdAndUsuarioId(transacaoId, usuarioID)
                .orElseThrow(() -> new TransacaoNaoEncontradaException("Transação não encontrada"));

        transacaoRepository.delete(buscarTransacao);
    }


    public SaldoResponseDTO calcularSaldo (UUID usuarioID) {

        List<TransacaoEntity> todasTransacoes = transacaoRepository.findByUsuarioId(usuarioID);

        //pega todas as transacoes de um usuario com nosso metodo do repository e filtra por receita ou despesa e mapeia
        BigDecimal receitas = todasTransacoes.stream()
                .filter(t -> t.getTipoTransacao() == TipoTransacao.RECEITA)
                .map(TransacaoEntity::getValor)
                .reduce(BigDecimal.ZERO , BigDecimal::add);

        BigDecimal despesas = todasTransacoes.stream()
                .filter(t -> t.getTipoTransacao() == TipoTransacao.DESPESA)
                .map(TransacaoEntity::getValor)
                // linha acima extrai o valor monetário de cada transação
                .reduce(BigDecimal.ZERO , BigDecimal::add);

        BigDecimal saldo = receitas.subtract(despesas);


        return new SaldoResponseDTO( receitas,despesas, saldo);
    }
    

}
