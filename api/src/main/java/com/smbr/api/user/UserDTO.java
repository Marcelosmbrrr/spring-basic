package com.smbr.api.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotBlank String username,
        @NotNull String name,
        @NotBlank String email,
        @NotBlank String password,
        @NotNull UserRole role
) {

}
