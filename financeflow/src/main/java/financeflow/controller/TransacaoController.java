package financeflow.controller;

import financeflow.dto.SaldoResponseDTO;
import financeflow.dto.TransacaoRequestDTO;
import financeflow.dto.TransacaoResponseDTO;
import financeflow.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios/{usuarioId}/transacoes")
@Validated
public class TransacaoController {
    private final TransacaoService transacaoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoResponseDTO criarTransacao ( @PathVariable UUID usuarioId,
                                                    @Valid @RequestBody TransacaoRequestDTO transacaoRequestDTO ) {

        return transacaoService.criarTransacao(usuarioId , transacaoRequestDTO);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TransacaoResponseDTO> listarTransacao( @PathVariable UUID usuarioId)
    {
        return transacaoService.listarTransacoes(usuarioId);
    }

    @GetMapping("{transacaoId}")
    @ResponseStatus (HttpStatus.OK)
    public TransacaoResponseDTO buscarTransacaoPorId (
                                          @PathVariable UUID usuarioId, @PathVariable UUID transacaoId) {

       return transacaoService.buscarPorId(usuarioId , transacaoId);
    }

    @PutMapping("{transacaoId}")
    @ResponseStatus(HttpStatus.OK)
    public TransacaoResponseDTO atualizarTransacao (
                                        @PathVariable UUID usuarioId, @PathVariable UUID transacaoId ,
                                        @Valid @RequestBody TransacaoRequestDTO transacaoRequestDto) {
       return transacaoService.atualizarTransacao(usuarioId , transacaoId , transacaoRequestDto);
    }

    @DeleteMapping("{transacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarTransacao(
                                    @PathVariable UUID usuarioId, @PathVariable UUID transacaoId) {
        transacaoService.deletarTransacao(usuarioId, transacaoId);
    }

    @GetMapping("/saldo")
    @ResponseStatus(HttpStatus.OK)
    public SaldoResponseDTO calcularSaldo (
                                   @PathVariable UUID usuarioId) {

       return transacaoService.calcularSaldo(usuarioId);
    }

    

}
