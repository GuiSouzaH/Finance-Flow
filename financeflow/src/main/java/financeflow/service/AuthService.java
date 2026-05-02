package financeflow.service;

import financeflow.dto.AuthResponseDTO;
import financeflow.dto.LoginRequestDTO;
import financeflow.exception.UsuarioNaoEncontradoException;
import financeflow.model.UsuarioEntity;
import financeflow.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    public AuthResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        UsuarioEntity usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        String token = jwtService.generateToken(usuario);


        return new AuthResponseDTO(token, "Bearer", usuario.getEmail(), usuario.getNomeCompleto());
    }
}
