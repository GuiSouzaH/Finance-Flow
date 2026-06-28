package financeflow.dto;

import financeflow.enums.ComprovanteStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record ComprovanteResponseDTO (
        UUID id,
        String nomeArquivo,
        ComprovanteStatus status,
        String tipoArquivo,
        LocalDateTime dataEnvioComprovante,
        String mensagemErro,
        UUID transacaoId

) {}
