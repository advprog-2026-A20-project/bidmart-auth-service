package id.ac.ui.cs.advprog.authservice.api.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenValidationRequest(@NotBlank String accessToken) {}
