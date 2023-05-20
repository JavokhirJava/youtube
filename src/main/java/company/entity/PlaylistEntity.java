package company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Table(name = "playlist")
@Entity
public class PlaylistEntity {
 //id,channel_id,name,description,status(private,public),order_num
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "channel_id")
    private Integer chanellId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "order_num")
    private Integer orderNum;

}
