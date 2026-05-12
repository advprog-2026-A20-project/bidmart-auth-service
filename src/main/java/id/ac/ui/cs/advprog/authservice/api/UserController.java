package id.ac.ui.cs.advprog.authservice.api;

import id.ac.ui.cs.advprog.authservice.api.dto.UserProfileResponse;
import id.ac.ui.cs.advprog.authservice.repository.UserRepository;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}/profile")
    public UserProfileResponse getProfile(@PathVariable String userId) {
        UUID id = UUID.fromString(userId);
        var user = userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new UserProfileResponse(
            user.getId().toString(),
            user.getEmail(),
            user.getRole().name(),
            "OK",
            "success"
        );
    }
}
