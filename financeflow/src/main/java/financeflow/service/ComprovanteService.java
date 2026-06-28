package financeflow.service;

import financeflow.dto.ComprovanteResponseDTO;
import financeflow.enums.ComprovanteStatus;
import financeflow.enums.TipoArquivoAceito;
import financeflow.exception.BusinessException;
import financeflow.exception.ComprovanteNaoEncontradoException;
import financeflow.exception.UsuarioNaoEncontradoException;
import financeflow.model.entity.ComprovanteEntity;
import financeflow.model.entity.UsuarioEntity;
import financeflow.repository.ComprovanteRepository;
import financeflow.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComprovanteService {

    private final ComprovanteRepository comprovanteRepository;
    private final UsuarioRepository usuarioRepository;

    private ComprovanteResponseDTO toDto (ComprovanteEntity entity) {
        UUID transacaoId = entity.getTransacao() != null
                ? entity.getTransacao().getId()
                : null;

        return new  ComprovanteResponseDTO(
                entity.getId(),
                entity.getNomeArquivo(),
                entity.getStatus(),
                entity.getTipoArquivo(),
                entity.getDataEnvioComprovante(),
                entity.getMensagemErro(),
                transacaoId
        );
    }

    public ComprovanteResponseDTO uploadComprovante (UUID usuarioId, MultipartFile arquivo) {
        if (arquivo.isEmpty()) {
            throw new BusinessException("Arquivo enviado está vazio");
        }

        if (!TipoArquivoAceito.isAceito(arquivo.getContentType())) {
            throw new BusinessException("Tipo de arquivo não aceito");
        }

        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));
        try {
            ComprovanteEntity novoComprovante = ComprovanteEntity.builder()
                    .usuario(usuario)
                    .nomeArquivo(arquivo.getOriginalFilename())
                    .tipoArquivo(arquivo.getContentType())
                    .dados(arquivo.getBytes())
                    .status(ComprovanteStatus.PENDENTE)
                    .build();

            ComprovanteEntity save = comprovanteRepository.save(novoComprovante);

            return toDto(save);

        } catch (IOException e) {
            throw new BusinessException("Erro ao salvar o arquivo" + e.getMessage());
        }
    }

    public ComprovanteResponseDTO buscarPorId (UUID usuarioId, UUID comprovanteId) {
        ComprovanteEntity comprovante = comprovanteRepository.findByIdAndUsuarioId(usuarioId, comprovanteId)
                .orElseThrow(() -> new ComprovanteNaoEncontradoException("Comprovante não encontrado"));


        return toDto(comprovante);
    }
}
