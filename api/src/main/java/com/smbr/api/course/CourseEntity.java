package com.smbr.api.course;

import com.smbr.api.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Data
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code", unique = true, nullable = false)
    @NotBlank(message = "Code is required")
    private String code;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Must be between 2 and 50 characters")
    private String name;

    @Column(name = "open", unique = true, nullable = false)
    @NotNull(message = "Open is required")
    private Boolean open;

    @Column(name = "score", nullable = false)
    @NotNull(message = "Score is mandatory")
    @DecimalMin(value = "0.0", inclusive = true, message = "Must be greater than or equal to 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Must be less than or equal to 100")
    private Double score;

    @ManyToMany(mappedBy = "courses")
    private Set<UserEntity> users = new HashSet<>();
}
