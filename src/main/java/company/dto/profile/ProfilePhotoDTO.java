package company.dto.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import company.dto.attach.AttachDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfilePhotoDTO {
    private Integer id;
    private String name;
    private String surname;
    private AttachDTO photo;
}
