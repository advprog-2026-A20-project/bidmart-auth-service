package id.ac.ui.cs.advprog.authservice.api.dto;

public record UserProfileResponse(
    String userId,
    String email,
    String role,
    String status,
    String message
) {
}
