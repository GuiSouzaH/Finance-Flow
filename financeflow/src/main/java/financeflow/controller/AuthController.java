package financeflow.controller;

import financeflow.dto.AuthResponseDTO;
import financeflow.dto.LoginRequestDTO;
import financeflow.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponseDTO login (@Valid @RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}
