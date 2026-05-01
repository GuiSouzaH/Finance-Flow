package financeflow.controller;

import financeflow.dto.AlertasResponseDTO;
import financeflow.dto.CategoriasResponseDTO;
import financeflow.dto.MensalResponseDTO;
import financeflow.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios/{usuarioId}/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/categorias")
    @ResponseStatus(HttpStatus.OK)
    public CategoriasResponseDTO calcularPorCategoria (@PathVariable UUID usuarioId) {

        return dashboardService.calcularPorCategoria(usuarioId);
    }

    @GetMapping("/mensal")
    @ResponseStatus(HttpStatus.OK)
    public MensalResponseDTO calcularPorMensal (@PathVariable UUID usuarioId) {
        return dashboardService.calcularMensal(usuarioId);
    }

    @GetMapping("/alertas")
    @ResponseStatus(HttpStatus.OK)
    public AlertasResponseDTO gerarAlertas (@PathVariable UUID usuarioId) {
        return dashboardService.gerarAlertas(usuarioId);
    }


}
