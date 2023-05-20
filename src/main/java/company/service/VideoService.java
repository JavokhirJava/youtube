package company.service;

import company.dto.attach.PreviewAttachDTO;
import company.dto.channel.ChannelShortInfoDTO;
import company.dto.video.VideShortInfoDTO;
import company.dto.video.VideoDTO;
import company.entity.ChannelEntity;
import company.entity.ProfileEntity;
import company.entity.VideoEntity;
import company.enums.GeneralStatus;
import company.exps.MethodNotAllowedException;
import company.repository.ChannelRepository;
import company.repository.ProfileRepository;
import company.repository.VideoRepository;
import company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private AttachService attachService;

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
    public List<VideShortInfoDTO> searchTitle(String title) {
        return toDoShortInfo(title);
    }

    public List<VideShortInfoDTO> toDoShortInfo(String title) {
        List<VideoEntity> list = videoRepository.findByTitle(title);
        List<VideShortInfoDTO> info = new LinkedList<>();
        for (VideoEntity entity : list) {
            VideShortInfoDTO dto = new VideShortInfoDTO();
            ChannelShortInfoDTO channel = channelService.getChannelShort(entity.getChannelId());
            PreviewAttachDTO preview = attachService.getPreviewAttach(entity.getPreviewAttachId());
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setViewCount(entity.getViewCount());
            dto.setPublishedDate(entity.getPublishedDate());
            dto.setChannelShortInfoDTO(channel);
            dto.setPreviewAttachDTO(preview);
            info.add(dto);
        }
        return info;
    }

    public Page<VideShortInfoDTO> pagingCategory(Integer categoryId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<VideoEntity> pageObj = videoRepository.findByCategoryId(categoryId, paging);
        return paging(paging, pageObj);
    }

    public Page<VideShortInfoDTO> pagingTag(Integer tagId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<VideoEntity> pageObj = videoRepository.findByTagId(tagId, paging);
        return paging(paging, pageObj);
    }

    public Page<VideShortInfoDTO> paging(Pageable paging, Page<VideoEntity> pageObj) {
        Long totalCount = pageObj.getTotalElements();
        List<VideoEntity> entityList = pageObj.getContent();
        List<VideShortInfoDTO> dtoList = new LinkedList<>();
        for (VideoEntity entity : entityList) {
            VideShortInfoDTO dto = new VideShortInfoDTO();
            ChannelShortInfoDTO channel = channelService.getChannelShort(entity.getChannelId());
            PreviewAttachDTO preview = attachService.getPreviewAttach(entity.getPreviewAttachId());
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setViewCount(entity.getViewCount());
            dto.setPublishedDate(entity.getPublishedDate());
            dto.setChannelShortInfoDTO(channel);
            dto.setPreviewAttachDTO(preview);
            dtoList.add(dto);
        }
        Page<VideShortInfoDTO> response = new PageImpl<VideShortInfoDTO>(dtoList, paging, totalCount);
        return response;
    }
}
