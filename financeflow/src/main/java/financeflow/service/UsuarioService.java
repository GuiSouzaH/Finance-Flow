package financeflow.service;

import financeflow.dto.UsuarioRequestDTO;
import financeflow.dto.UsuarioResponseDTO;
import financeflow.dto.UsuarioUpdateDTO;
import financeflow.enums.Roles;
import financeflow.exception.UsuarioJaCadastradoException;
import financeflow.exception.UsuarioNaoEncontradoException;
import financeflow.model.UsuarioEntity;
import financeflow.repository.TransacaoRepository;
import financeflow.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TransacaoRepository transacaoRepository;

    private UsuarioResponseDTO toResponseDTO(UsuarioEntity entity) {
        return new UsuarioResponseDTO(
                entity.getId(),
                entity.getNomeCompleto(),
                entity.getEmail(),
                entity.getDataCriacao()
        );
    }



    public UsuarioResponseDTO criarUsuario (  UsuarioRequestDTO usuarioRequestDTO) {



        if (usuarioRepository.existsByEmail(usuarioRequestDTO.email())) {
               throw  new UsuarioJaCadastradoException ("Email já cadastrado!");
        }

        //Criptografa a senha
        String senhaCriptografa = bCryptPasswordEncoder.encode(usuarioRequestDTO.senha());

        UsuarioEntity novoUsuario = UsuarioEntity.builder()
                .nomeCompleto(usuarioRequestDTO.nomeCompleto())
                .email(usuarioRequestDTO.email())
                .senha(senhaCriptografa)
                .role(Roles.USER) // Define todo novo usuario padrão como USER
                .build();

        UsuarioEntity usuarioSalvo = usuarioRepository.save(novoUsuario);
        return toResponseDTO(usuarioSalvo);

    }

    public List<UsuarioResponseDTO> listarUsuarios () {

        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId (UUID id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não foi encontrado"));

        return toResponseDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDTO atualizarUsuario (UUID id, UsuarioUpdateDTO usuarioUpdateDTO) {

        UsuarioEntity usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException ("Usuário não foi encontrado"));



        // Atualiza nome se fornecido
        if (usuarioUpdateDTO.nomeCompleto() != null) {
            usuarioExistente.setNomeCompleto(usuarioUpdateDTO.nomeCompleto());
        }
        // Valida se o novo e-mail (se enviado) já pertence a outra pessoa
        if (usuarioUpdateDTO.email() != null) {
            if (usuarioRepository.existsByEmail(usuarioUpdateDTO.email())) {
                throw new UsuarioJaCadastradoException("Email já cadastrado!");
            }

            usuarioExistente.setEmail(usuarioUpdateDTO.email());
        }

            UsuarioEntity usuarioAtualizado = usuarioRepository.save(usuarioExistente);

            return toResponseDTO(usuarioAtualizado);

    }

    @Transactional
    public void deletarUsuario(UUID id) {
        transacaoRepository.deleteByUsuarioId(id);
        usuarioRepository.deleteById(id);
    }
}
