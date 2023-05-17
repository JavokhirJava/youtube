package company.entity;

import company.enums.GeneralStatus;
import company.enums.NotificationTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "subscription")
@Entity
public class SubscriptionEntity {
//    id,profile_id,channel_id,created_date, unsubscribe_date, status (active,block),notification_type(All,Personalized,Non)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",insertable=false, updatable=false)
    private ProfileEntity profile;
    @Column(name = "channel_id")
    private String channelId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id",insertable=false, updatable=false)
    private ChannelEntity channel;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "unsubscribe_date")
    private LocalDateTime unsubscribeDate;
    @Column(name = "status")
    private GeneralStatus status;
    @Column(name = "notification_type")
    private NotificationTypeEnum notificationType;
}
