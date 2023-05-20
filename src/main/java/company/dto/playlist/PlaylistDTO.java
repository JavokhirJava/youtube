package company.dto.playlist;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlaylistDTO {
    private Integer id;
    private Integer channel_id;
    private String name;
    private String description;
    private Integer order_num;
}
