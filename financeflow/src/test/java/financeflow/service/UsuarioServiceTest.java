package financeflow.service;

import financeflow.dto.UsuarioRequestDTO;
import financeflow.dto.UsuarioResponseDTO;
import financeflow.exception.UsuarioJaCadastradoException;
import financeflow.exception.UsuarioNaoEncontradoException;
import financeflow.repository.TransacaoRepository;
import financeflow.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import financeflow.model.UsuarioEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {

        //Arrange
        UUID id = UUID.randomUUID();
        UsuarioEntity usuarioFalso = new UsuarioEntity();
        usuarioFalso.setId(id);
        usuarioFalso.setEmail("Gui124590@gmail.com");
        usuarioFalso.setNomeCompleto("Gui");


        //ac
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioFalso));

        UsuarioResponseDTO buscar = usuarioService.buscarPorId(id);

        assertNotNull(buscar);
        Assertions.assertEquals("Gui", buscar.nomeCompleto());

    }


    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {

        UUID id = UUID.randomUUID();



        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioService.buscarPorId(id);
        });
    }

    @Test
    void deveLancarExcecaoQuandoEmailExistir() {
        UUID id = UUID.randomUUID();
        UsuarioEntity usuarioFalso = new UsuarioEntity();
        usuarioFalso.setId(id);
        usuarioFalso.setEmail("Gui124590@gmail.com");

        UsuarioRequestDTO usuarioTeste = new UsuarioRequestDTO(
                "Gui", usuarioFalso.getEmail(), "123456");
        when(usuarioRepository.existsByEmail(usuarioTeste.email())).thenReturn(true);

        //ACT + ASSERT
        assertThrows(UsuarioJaCadastradoException.class, () -> {
            usuarioService.criarUsuario(usuarioTeste);
        });

    }

    @Test
    void emailCorretoSucesso () {
        UUID id = UUID.randomUUID();
        UsuarioEntity usuarioFalso = new UsuarioEntity();
        usuarioFalso.setId(id);
       usuarioFalso.setDataCriacao(LocalDateTime.now());
        usuarioFalso.setNomeCompleto("Guilherme Henrique Souza Fernandes");
        usuarioFalso.setEmail("Guilherme124590@gmail.com");

        UsuarioRequestDTO usuarioTeste = new UsuarioRequestDTO(
                "Guilherme Henrique Souza Fernandes", "Guilherme124590@gmail.com", "123456"
        );


        when(usuarioRepository.existsByEmail("Guilherme124590@gmail.com")).thenReturn(false);
        when(bCryptPasswordEncoder.encode("123456")).thenReturn("senhaCriptografada");
        when(usuarioRepository.save(any())).thenReturn(usuarioFalso);

        UsuarioResponseDTO  resultado = usuarioService.criarUsuario(usuarioTeste);


        assertNotNull(resultado);
        assertEquals("Guilherme Henrique Souza Fernandes", resultado.nomeCompleto());
        assertEquals("Guilherme124590@gmail.com", resultado.email());
    }

    @Test
    void listarUsuarios() {
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void atualizarUsuario() {
    }

    @Test
    void deletarUsuario() {
    }



}