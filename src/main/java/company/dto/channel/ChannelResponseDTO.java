package company.dto.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import company.dto.attach.AttachDTO;
import company.dto.profile.ProfileDTO;
import company.dto.profile.ProfilePhotoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelResponseDTO {
    private String id;
    private String name;
    private String description;
    private String photoId;
    private String bannerId;
    private ProfilePhotoDTO profile;
    private AttachDTO photo;
    private AttachDTO banner;
}
