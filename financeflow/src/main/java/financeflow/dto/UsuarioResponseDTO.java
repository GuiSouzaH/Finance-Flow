package financeflow.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(UUID Id,
                                 String nomeCompleto,
                                 String email,
                                 LocalDateTime dataCriacao) {
}
