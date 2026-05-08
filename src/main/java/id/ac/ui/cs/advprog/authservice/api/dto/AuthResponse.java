package id.ac.ui.cs.advprog.authservice.api.dto;

public record AuthResponse(
        String status,
        String accessToken,
        String refreshToken,
        String message
) {
    public static AuthResponse todo(String message) {
        return new AuthResponse("TODO", null, null, message);
    }
}
