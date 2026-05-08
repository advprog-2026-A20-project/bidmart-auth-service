package id.ac.ui.cs.advprog.authservice.api;

import id.ac.ui.cs.advprog.authservice.api.dto.AuthResponse;
import id.ac.ui.cs.advprog.authservice.api.dto.LoginRequest;
import id.ac.ui.cs.advprog.authservice.api.dto.LogoutRequest;
import id.ac.ui.cs.advprog.authservice.api.dto.RefreshRequest;
import id.ac.ui.cs.advprog.authservice.api.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return AuthResponse.todo("TODO: implement register flow with persistent user store");
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return AuthResponse.todo("TODO: implement login with password verification and JWT issuance");
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@Valid @RequestBody RefreshRequest request) {
        return AuthResponse.todo("TODO: implement refresh token rotation");
    }

    @PostMapping("/logout")
    public AuthResponse logout(@Valid @RequestBody LogoutRequest request) {
        return AuthResponse.todo("TODO: implement logout and token/session revocation");
    }
}
