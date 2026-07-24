package ecommerce_backend.ecommerce_backend.dto;

import ecommerce_backend.ecommerce_backend.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthDTO {

    public record LoginRequest(
            @Email
            @NotBlank
            String email,

            @NotBlank
            String password

    ) {}

    public record RegisterRequest(
            @NotBlank
            String name,

            @Email
            @NotBlank
            String email,

            @Size(min = 6)
            String password,
            Role role

    ) {}

    public record Response(
            String token,
            String message
    ) {}

}