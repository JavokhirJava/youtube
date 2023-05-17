package company.dto.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {
    private String id;
    @NotNull(message = "name required")
    private String name;
    private String description;
    private String photoId;
    private String bannerId;
    private Integer profileId;
}
