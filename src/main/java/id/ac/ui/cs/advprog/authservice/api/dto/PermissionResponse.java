package id.ac.ui.cs.advprog.authservice.api.dto;

import java.util.List;

public record PermissionResponse(
    String userId,
    List<String> permissions,
    String status,
    String message
) {
}
