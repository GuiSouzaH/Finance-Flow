package financeflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(@NotBlank String nomeCompleto,
                                @Email @NotBlank String email,
                                @NotBlank String senha) {
}
