package company.entity;

import company.dto.channel.ChannelDTO;
import company.enums.GeneralStatus;
import company.enums.TypeEnums;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Table(name = "video")
@Entity
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "category_id")
    private Integer categoryId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;
    @Column(name = "attach_id")
    private String attachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity photo;
    @Column(name = "preview_attach_id")
    private String previewAttachId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preview_attach_id", insertable = false, updatable = false)
    private AttachEntity previewAttach;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GeneralStatus status = GeneralStatus.PUBLIC;
    @Column(name = "published_date")
    private LocalDateTime publishedDate;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "view_count")
    private Integer viewCount=0;
    @Column(name = "like_count")
    private Integer likeCount=0;
    @Column(name = "dislike_count")
    private Integer dislikeCount=0;
    @Column(name = "shared_count")
    private Integer sharedCount=0;
    @Column(name = "description")
    private String description;
    @Column(name = "channel_id")
    private String channelId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    private ChannelEntity channel;
    @Column(name = "title")
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeEnums type;
}

