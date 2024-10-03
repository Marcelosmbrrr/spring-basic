package com.smbr.api.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO (
        @NotBlank String code,
        @NotNull String name,
        @NotNull Boolean open,
        @NotNull Double score
) {

}
