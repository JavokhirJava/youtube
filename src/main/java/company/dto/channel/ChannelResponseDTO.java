package company.dto.channel;

import company.dto.ProfileDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelResponseDTO {
    private String id;
    private String name;
    private String photoId;
    private String description;
    private String bannerId;
    private ProfileDTO profile;
}
