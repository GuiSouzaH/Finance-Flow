package financeflow.controller;


import financeflow.dto.UsuarioResponseDTO;
import financeflow.dto.UsuarioUpdateDTO;
import financeflow.security.UsuarioDetails;
import financeflow.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/me")
@Validated
public class MeController {

    private final UsuarioService usuarioService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO buscarUsuario (@AuthenticationPrincipal UsuarioDetails  usuarioDetails) {
        UUID id = usuarioDetails.getId();
        return usuarioService.buscarPorId(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO  atualizarUsuario ( @AuthenticationPrincipal UsuarioDetails  usuarioDetails ,@RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {
        UUID id = usuarioDetails.getId();
        return usuarioService.atualizarUsuario(id, usuarioUpdateDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario (@AuthenticationPrincipal UsuarioDetails  usuarioDetails) {
        UUID id = usuarioDetails.getId();
        usuarioService.deletarUsuario(id);
    }

}
