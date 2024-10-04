package com.smbr.api.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull String username,
        @NotNull String name,
        @NotNull String email,
        @NotNull String password,
        @NotNull UserRole role
) {

}
