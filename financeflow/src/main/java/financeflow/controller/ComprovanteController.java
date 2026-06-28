package financeflow.controller;

import financeflow.dto.ComprovanteResponseDTO;
import financeflow.security.UsuarioDetails;
import financeflow.service.ComprovanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/me/comprovantes")
public class ComprovanteController  {

    private final ComprovanteService comprovanteService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ComprovanteResponseDTO uploadComprovante (
            @AuthenticationPrincipal UsuarioDetails usuarioDetails,
            @RequestParam ("arquivo") MultipartFile arquivo
    ) {

        UUID usuarioId = usuarioDetails.getId();
        return comprovanteService.uploadComprovante(usuarioId, arquivo);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ComprovanteResponseDTO buscarPorId(
            @AuthenticationPrincipal UsuarioDetails usuarioDetails,
            @PathVariable UUID id) {
        return comprovanteService.buscarPorId(usuarioDetails.getId(), id);
    }

}
