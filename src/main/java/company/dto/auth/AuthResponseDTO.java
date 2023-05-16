package company.dto.auth;

import company.enums.ProfileRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {
    @NotNull(message = "Name required")
    private String name;
    @NotNull(message = "Surname required")
    private String surname;
    @NotNull(message = "Role required")
    private ProfileRole role;
    @NotNull(message = "JWT required")
    private String jwt;
}
