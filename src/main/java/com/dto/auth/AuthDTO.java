package com.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {
    @NotNull(message = "Email required")
    private String email;
    @NotNull(message = "Password required")
    private String password;
}
