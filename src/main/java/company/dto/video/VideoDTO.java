package company.dto.video;

import company.enums.GeneralStatus;
import company.enums.TypeEnums;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideoDTO {
    private String id;
    private Integer categoryId;
    private String attachId;
    private String previewAttachId;
    private GeneralStatus status;
    private LocalDateTime publishedDate;
    private Integer viewCount;
    private LocalDateTime createdDate;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer sharedCount;
    private String description;
    private String title;
    private TypeEnums type;
    private String channelId;
}
