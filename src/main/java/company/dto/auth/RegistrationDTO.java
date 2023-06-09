package company.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    @NotNull(message = "Name required")
    private String name;
    @NotNull(message = "Surname required")
    private String surname;
    @NotNull(message = "Email required")
    private String email;
    @NotNull(message = "Password required")
    private String password;
}
