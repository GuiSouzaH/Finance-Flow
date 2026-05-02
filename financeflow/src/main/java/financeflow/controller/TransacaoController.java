package financeflow.controller;

import financeflow.dto.SaldoResponseDTO;
import financeflow.dto.TransacaoRequestDTO;
import financeflow.dto.TransacaoResponseDTO;
import financeflow.security.UsuarioDetails;
import financeflow.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/me/transacoes")
@Validated
public class TransacaoController {
    private final TransacaoService transacaoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoResponseDTO criarTransacao ( @AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                    @Valid @RequestBody TransacaoRequestDTO transacaoRequestDTO ) {
        UUID usuarioId = usuarioDetails.getId();
        return transacaoService.criarTransacao(usuarioId , transacaoRequestDTO);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TransacaoResponseDTO> listarTransacoes( @AuthenticationPrincipal UsuarioDetails usuarioDetails)
    {
        UUID usuarioId = usuarioDetails.getId();
        return transacaoService.listarTransacoes(usuarioId);
    }

    @GetMapping("/{transacaoId}")
    @ResponseStatus (HttpStatus.OK)
    public TransacaoResponseDTO buscarTransacaoPorId (@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                      @PathVariable UUID transacaoId) {
        UUID usuarioId = usuarioDetails.getId();
       return transacaoService.buscarPorId(usuarioId , transacaoId);
    }

    @PutMapping("/{transacaoId}")
    @ResponseStatus(HttpStatus.OK)
    public TransacaoResponseDTO atualizarTransacao (@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                                    @PathVariable UUID transacaoId ,
                                                    @Valid @RequestBody TransacaoRequestDTO transacaoRequestDto) {

        UUID usuarioId = usuarioDetails.getId();
        return transacaoService.atualizarTransacao(usuarioId , transacaoId , transacaoRequestDto);
    }

    @DeleteMapping("/{transacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarTransacao(@AuthenticationPrincipal UsuarioDetails usuarioDetails ,
                                 @PathVariable UUID transacaoId) {
        UUID usuarioId = usuarioDetails.getId();
        transacaoService.deletarTransacao(usuarioId, transacaoId);
    }

}
