package company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import company.dto.profile.ProfileDTO;
import company.dto.profile.ProfilePhotoDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentInfoDTO {
    private Integer id;
    private String content;
    private LocalDateTime createdDate;
    private Integer likeCount;
    private Integer dislikeCount;
    private ProfilePhotoDTO profile;
}
