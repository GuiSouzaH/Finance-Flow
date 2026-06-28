package financeflow.controller;

import financeflow.dto.UsuarioRequestDTO;
import financeflow.dto.UsuarioResponseDTO;
import financeflow.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO criarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.criarUsuario(usuarioRequestDTO);
    }
}