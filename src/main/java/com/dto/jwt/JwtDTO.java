package com.dto.jwt;

import com.enums.ProfileRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtDTO {
    @NotNull(message = "email required")
    private String email;
    @NotNull(message = "role required")
    private ProfileRole role;
}
