package id.ac.ui.cs.advprog.authservice.internal;

import id.ac.ui.cs.advprog.authservice.api.dto.PermissionResponse;
import id.ac.ui.cs.advprog.authservice.api.dto.TokenValidationRequest;
import id.ac.ui.cs.advprog.authservice.api.dto.TokenValidationResponse;
import id.ac.ui.cs.advprog.authservice.model.Role;
import id.ac.ui.cs.advprog.authservice.repository.UserRepository;
import id.ac.ui.cs.advprog.authservice.security.JwtService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class InternalAuthController {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public InternalAuthController(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/auth/validate-token")
    public TokenValidationResponse validateToken(@RequestBody TokenValidationRequest request) {
        String token = request.accessToken();
        if (!jwtService.isValid(token)) {
            return new TokenValidationResponse(false, null, null, null, "Invalid token");
        }

        return new TokenValidationResponse(
            true,
            jwtService.extractUserId(token),
            jwtService.extractRole(token),
            jwtService.extractEmail(token),
            "Valid token"
        );
    }

    @GetMapping("/users/{userId}/permissions")
    public PermissionResponse getPermissions(@PathVariable String userId) {
        var user = userRepository.findById(UUID.fromString(userId)).orElse(null);
        if (user == null) {
            return new PermissionResponse(userId, List.of(), "NOT_FOUND", "User not found");
        }

        List<String> permissions = switch (user.getRole()) {
            case ADMIN -> List.of("AUTH_ADMIN", "LISTING_WRITE", "AUCTION_WRITE", "WALLET_WRITE");
            case SELLER -> List.of("LISTING_WRITE", "AUCTION_WRITE");
            case BUYER -> List.of("BID_WRITE", "WALLET_TOPUP");
        };

        return new PermissionResponse(userId, permissions, "OK", "success");
    }
}
