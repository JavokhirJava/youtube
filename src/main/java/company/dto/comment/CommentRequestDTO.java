package company.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    private Integer id;
    @NotNull(message = "video required")
    private String videoId;
    @NotNull(message = "content required")
    private String content;
    private Integer replyId;
}
