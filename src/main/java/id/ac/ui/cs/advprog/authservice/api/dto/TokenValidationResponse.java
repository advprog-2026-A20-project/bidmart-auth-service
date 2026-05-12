package id.ac.ui.cs.advprog.authservice.api.dto;

public record TokenValidationResponse(
    boolean valid,
    String userId,
    String role,
    String email,
    String message
) {
}
