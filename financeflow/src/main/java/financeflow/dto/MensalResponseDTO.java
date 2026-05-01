package financeflow.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public record MensalResponseDTO(LocalDate periodoReferencia,
                                BigDecimal totalReceitas,
                                BigDecimal totalDespesas,
                                BigDecimal saldo) {

    //metodo para formatar a data e ficar sem problema de serializacao no JSON

    public String dataMesAno() {
        // MM = mês com 2 dígitos, yyyy = ano com 4 dígitos
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("MM/yyyy");
        return periodoReferencia.format(formatador);
    }
}
