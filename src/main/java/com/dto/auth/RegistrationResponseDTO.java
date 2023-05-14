package com.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationResponseDTO {
    @NotNull(message = "Message required")
    private String massage;

    public RegistrationResponseDTO(String massage) {
        this.massage = massage;
    }
}
