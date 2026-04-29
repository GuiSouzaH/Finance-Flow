package financeflow.dto;

import financeflow.enums.CategoriaTransacao;

import java.math.BigDecimal;

import java.util.List;


//retorna categoria + valor gasto nela
public record CategoriasResponseDTO(List<CategoriaGastoDTO> categorias) {
}
