package id.ac.ui.cs.advprog.authservice.api.dto;

public record TokenValidationResponse(
        boolean valid,
        String userId,
        String message
) {
    public static TokenValidationResponse todo(String message) {
        return new TokenValidationResponse(false, null, message);
    }
}
