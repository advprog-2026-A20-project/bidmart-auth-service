package id.ac.ui.cs.advprog.authservice.api.dto;

import java.util.List;

public record PermissionResponse(
        String userId,
        List<String> permissions,
        String status,
        String message
) {
    public static PermissionResponse todo(String userId, String message) {
        return new PermissionResponse(userId, List.of(), "TODO", message);
    }
}
