package company.dto.videoTag;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoTagRequestDTO {
    @NotNull(message = "video required")
    private String videoId;
    @NotNull(message = "tag required")
    private Integer tagId;
}
