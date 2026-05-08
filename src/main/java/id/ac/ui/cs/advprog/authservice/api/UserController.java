package id.ac.ui.cs.advprog.authservice.api;

import id.ac.ui.cs.advprog.authservice.api.dto.UserProfileResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{userId}/profile")
    public UserProfileResponse getProfile(@PathVariable String userId) {
        return UserProfileResponse.todo(userId, "TODO: return profile from auth-owned user table");
    }
}
