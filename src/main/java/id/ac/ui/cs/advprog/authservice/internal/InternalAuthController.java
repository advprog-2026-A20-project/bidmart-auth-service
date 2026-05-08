package id.ac.ui.cs.advprog.authservice.internal;

import id.ac.ui.cs.advprog.authservice.api.dto.PermissionResponse;
import id.ac.ui.cs.advprog.authservice.api.dto.TokenValidationRequest;
import id.ac.ui.cs.advprog.authservice.api.dto.TokenValidationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class InternalAuthController {

    @PostMapping("/auth/validate-token")
    public TokenValidationResponse validateToken(@RequestBody TokenValidationRequest request) {
        return TokenValidationResponse.todo("TODO: validate JWT/access token with key rotation support");
    }

    @GetMapping("/users/{userId}/permissions")
    public PermissionResponse getPermissions(@PathVariable String userId) {
        return PermissionResponse.todo(userId, "TODO: resolve role-permission from auth service data store");
    }
}
