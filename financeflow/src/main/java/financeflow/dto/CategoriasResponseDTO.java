package financeflow.dto;

import java.util.List;


//retorna categoria + valor gasto nela
public record CategoriasResponseDTO(List<CategoriaGastoDTO> categorias) {
}
