package id.ac.ui.cs.advprog.authservice.api.dto;

public record LoginResponse(
    String accessToken,
    UserSummary user
) {
}
