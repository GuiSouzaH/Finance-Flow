package financeflow.dto;

import java.math.BigDecimal;

public record SaldoResponseDTO(BigDecimal totalReceitas,
                               BigDecimal totalDespesas,
                               BigDecimal saldo

) {
}
