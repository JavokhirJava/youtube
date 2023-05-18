package company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "comment")
@Entity
public class CommentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",insertable=false, updatable=false)
    private ProfileEntity profile;
    @Column(name = "video_id")
    private String videoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id",insertable=false, updatable=false)
    private VideoEntity video;
    @Column(name = "content",columnDefinition = "text")
    private String content;
    @Column(name = "reply_id")
    private Integer replyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id",insertable=false, updatable=false)
    private CommentEntity replyComment;
    @Column(name = "like_count")
    private Integer likeCount=0;
    @Column(name = "dislike_count")
    private Integer dislikeCount=0;
    @Column(name = "created_date")
    private LocalDateTime createdDate=LocalDateTime.now();
}
