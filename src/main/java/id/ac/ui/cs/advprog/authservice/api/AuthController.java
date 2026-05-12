package id.ac.ui.cs.advprog.authservice.api;

import id.ac.ui.cs.advprog.authservice.api.dto.LoginRequest;
import id.ac.ui.cs.advprog.authservice.api.dto.LoginResponse;
import id.ac.ui.cs.advprog.authservice.api.dto.RegisterRequest;
import id.ac.ui.cs.advprog.authservice.api.dto.RegisterResponse;
import id.ac.ui.cs.advprog.authservice.api.dto.UserSummary;
import id.ac.ui.cs.advprog.authservice.security.AuthenticatedUser;
import id.ac.ui.cs.advprog.authservice.service.AuthDomainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthDomainService authDomainService;

    public AuthController(AuthDomainService authDomainService) {
        this.authDomainService = authDomainService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return authDomainService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authDomainService.login(request);
    }

    @GetMapping("/me")
    public UserSummary me(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        return authDomainService.me(authenticatedUser.id());
    }
}
