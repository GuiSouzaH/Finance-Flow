package financeflow.dto;

public record AuthResponseDTO(String token,
                              String type,
                              String email,
                              String nome) {
}
