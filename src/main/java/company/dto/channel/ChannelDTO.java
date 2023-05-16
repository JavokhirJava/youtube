package company.dto.channel;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelDTO {
    private String id;
    @NotNull(message = "name required")
    private String name;
    private String description;
    private String photoId;
    private String bannerId;
}
