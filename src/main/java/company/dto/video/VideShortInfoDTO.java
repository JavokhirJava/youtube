package company.dto.video;

import company.dto.attach.PreviewAttachDTO;
import company.dto.channel.ChannelShortInfoDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideShortInfoDTO {
 private String id;
 private String title;
 private PreviewAttachDTO previewAttachDTO;
 private LocalDateTime  publishedDate;
 private ChannelShortInfoDTO channelShortInfoDTO;
 private Integer viewCount;

}
