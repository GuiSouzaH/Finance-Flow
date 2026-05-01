package financeflow.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(UUID id,
                                 String nomeCompleto,
                                 String email,
                                 LocalDateTime dataCriacao) {
}
