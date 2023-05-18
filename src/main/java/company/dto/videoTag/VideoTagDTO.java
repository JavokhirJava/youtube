package company.dto.videoTag;

import company.dto.tag.TagDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideoTagDTO {
    private String videoId;
    private TagDTO tag;
    private LocalDateTime createdDate;
}
