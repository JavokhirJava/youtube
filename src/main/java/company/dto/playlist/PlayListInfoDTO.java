package company.dto.playlist;

import com.fasterxml.jackson.annotation.JsonInclude;
import company.dto.channel.ChannelPhotoDTO;
import company.dto.profile.ProfilePhotoDTO;
import company.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayListInfoDTO {
    private Integer id;
    private String name;
    private String description;
    private GeneralStatus status;
    private Integer orderNum;
    private ChannelPhotoDTO channel;
    private ProfilePhotoDTO profile;
}
