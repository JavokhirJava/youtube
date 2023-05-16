package company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import company.enums.GeneralStatus;
import company.enums.ProfileRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;
    @NotBlank @Size(min = 5,max = 20,message = "Name is required")
    private String name;
    @NotBlank @Size(min = 5,max = 20,message = "Surname is required")
    private String surname;
    @NotBlank
    @Size(min = 4,max = 20,message = "Password must contain 4 characters")
    private String password;
    private String email;
    private Boolean visible;
    private ProfileRole role;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private String photoId;
    private GeneralStatus status;
    private String jwt;
}
