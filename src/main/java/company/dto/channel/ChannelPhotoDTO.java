package company.dto.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import company.dto.attach.AttachDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelPhotoDTO {
    private String id;
    private String name;
    private AttachDTO photo;
}
