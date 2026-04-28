package financeflow.dto;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public record ErrorResponseDTO(HttpStatus status,
                               String mensagem,
                               LocalDateTime data) {
}
