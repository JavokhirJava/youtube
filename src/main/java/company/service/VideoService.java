package company.service;

import company.dto.video.VideoDTO;
import company.entity.CategoryEntity;
import company.entity.ChannelEntity;
import company.entity.ProfileEntity;
import company.entity.VideoEntity;
import company.enums.GeneralStatus;
import company.exps.AppBadRequestException;
import company.exps.ItemNotFoundException;
import company.exps.MethodNotAllowedException;
import company.repository.ChannelRepository;
import company.repository.ProfileRepository;
import company.repository.VideoRepository;
import company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ChannelRepository channelRepository;

    public VideoDTO create(VideoDTO dto) {
        VideoEntity entity = new VideoEntity();
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        entity.setDescription(dto.getDescription());
        entity.setChannelId(dto.getChannelId());
        entity.setTitle(dto.getTitle());
        entity.setPreviewAttachId(dto.getPreviewAttachId());
        entity.setType(dto.getType());
        dto.setId(entity.getId());
        videoRepository.save(entity);
        return dto;
    }

    public VideoDTO update(String id, VideoDTO dto) {
        Optional<VideoEntity> video = videoRepository.findById(id);
        Optional<ChannelEntity> channel = channelRepository.findById(video.get().getChannelId());
        Optional<ProfileEntity> profile = profileRepository.findById(channel.get().getProfileId());
        Integer owner = SpringSecurityUtil.getProfileId();
        if (owner != profile.get().getId()) {
            throw new MethodNotAllowedException("it's not owner ");
        }
        VideoEntity entity = video.get();
        entity.setCategoryId(dto.getCategoryId());
        entity.setDescription(dto.getDescription());
        entity.setChannelId(dto.getChannelId());
        entity.setTitle(dto.getTitle());
        entity.setPreviewAttachId(dto.getPreviewAttachId());
        entity.setType(dto.getType());
        dto.setId(entity.getId());
        videoRepository.save(entity);
        return dto;
    }

    public Boolean changeStatus(String id) {
        Optional<VideoEntity> video = videoRepository.findById(id);
        Optional<ChannelEntity> channel = channelRepository.findById(video.get().getChannelId());
        Optional<ProfileEntity> profile = profileRepository.findById(channel.get().getProfileId());
        Integer owner = SpringSecurityUtil.getProfileId();
        VideoEntity entity = video.get();
        if (owner != profile.get().getId()) {
            throw new MethodNotAllowedException("it's not owner ");
        }
        if (video.get().getStatus().equals(GeneralStatus.PUBLIC)){
            entity.setStatus(GeneralStatus.PRIVATE);
            videoRepository.save(entity);
            return  true;
        }else {
            entity.setStatus(GeneralStatus.PUBLIC);
            videoRepository.save(entity);
            return true;
        }
    }
    public Boolean viewCount(String id) {
        int count=videoRepository.viewCount(id);
        return true;
    }
}
