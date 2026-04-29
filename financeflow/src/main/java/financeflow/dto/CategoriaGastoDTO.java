package financeflow.dto;

import financeflow.enums.CategoriaTransacao;

import java.math.BigDecimal;

public record CategoriaGastoDTO(CategoriaTransacao categoria,
                                BigDecimal valorGasto) {
}
