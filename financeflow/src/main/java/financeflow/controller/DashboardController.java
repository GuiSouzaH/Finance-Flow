package financeflow.controller;

import financeflow.dto.AlertasResponseDTO;
import financeflow.dto.CategoriasResponseDTO;
import financeflow.dto.MensalResponseDTO;
import financeflow.dto.SaldoResponseDTO;
import financeflow.security.UsuarioDetails;
import financeflow.service.DashboardService;
import financeflow.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/me/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    private final TransacaoService transacaoService;

    @GetMapping("/categorias")
    @ResponseStatus(HttpStatus.OK)
    public CategoriasResponseDTO calcularPorCategoria(@AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        UUID usuarioId = usuarioDetails.getId();
        return dashboardService.calcularPorCategoria(usuarioId);
    }

    @GetMapping("/mensal")
    @ResponseStatus(HttpStatus.OK)
    public MensalResponseDTO calcularPorMensal(@AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        UUID usuarioId = usuarioDetails.getId();
        return dashboardService.calcularMensal(usuarioId);
    }

    @GetMapping("/alertas")
    @ResponseStatus(HttpStatus.OK)
    public AlertasResponseDTO gerarAlertas(@AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        UUID usuarioId = usuarioDetails.getId();
        return dashboardService.gerarAlertas(usuarioId);
    }

    @GetMapping("/saldo")
    @ResponseStatus(HttpStatus.OK)
    public SaldoResponseDTO calcularSaldo(@AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        UUID usuarioId = usuarioDetails.getId();
        return transacaoService.calcularSaldo(usuarioId);
    }
}