package financeflow.controller;


import financeflow.dto.UsuarioRequestDTO;
import financeflow.dto.UsuarioResponseDTO;
import financeflow.dto.UsuarioUpdateDTO;
import financeflow.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO criarUsuario (@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.criarUsuario(usuarioRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponseDTO> listarUsuarios () {
        return usuarioService.listarUsuarios();
    }

    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO buscarUsuario (@PathVariable UUID id) {
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO  atualizarUsuario ( @PathVariable UUID id ,@RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {
        return usuarioService.atualizarUsuario(id, usuarioUpdateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario (@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
    }

}
