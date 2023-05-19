package company.dto.channel;

import company.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelRequestDTO {
    private String id;
    private String name;
    private String description;
    private GeneralStatus status;
}
