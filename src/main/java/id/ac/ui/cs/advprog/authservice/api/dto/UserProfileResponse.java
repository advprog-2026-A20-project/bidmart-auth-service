package id.ac.ui.cs.advprog.authservice.api.dto;

public record UserProfileResponse(
        String userId,
        String email,
        String fullName,
        String status,
        String message
) {
    public static UserProfileResponse todo(String userId, String message) {
        return new UserProfileResponse(userId, null, null, "TODO", message);
    }
}
