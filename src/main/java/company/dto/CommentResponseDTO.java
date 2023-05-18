package company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import company.entity.VideoEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDTO {
    private Integer id;
    private String content;
    private VideoEntity video;
    private LocalDateTime createdDate;
    private Integer likeCount;
    private Integer dislikeCount;
}
