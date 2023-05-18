package company.service;

import company.dto.videoTag.VideoTagDTO;
import company.dto.videoTag.VideoTagRequestDTO;
import company.entity.TagEntity;
import company.entity.VideoEntity;
import company.entity.VideoTagEntity;
import company.exps.AppBadRequestException;
import company.repository.VideoTagRepository;
import company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoTagService {
    @Autowired
    private VideoTagRepository videoTagRepository;
    @Autowired
    private TagService tagService;

    public VideoTagDTO create(VideoTagRequestDTO dto) {
        Optional<VideoTagEntity> optional = videoTagRepository.findByTagId(dto.getTagId());
        if (optional.isPresent()){
            throw  new AppBadRequestException("video tag exists");
        }
        VideoTagEntity entity = new VideoTagEntity();
        entity.setVideoId(dto.getVideoId());
        entity.setTagId(dto.getTagId());
        entity.setProfileId(SpringSecurityUtil.getProfileId());
        videoTagRepository.save(entity);
        return entityToVideoTagDTO(entity);
    }

    private VideoTagDTO entityToVideoTagDTO(VideoTagEntity entity) {
        VideoTagDTO videoTagDTO = new VideoTagDTO();
        videoTagDTO.setVideoId(entity.getVideoId());
        videoTagDTO.setCreatedDate(entity.getCreatedDate());
        videoTagDTO.setTag(tagService.getTagDTO(entity.getTagId()));
        return videoTagDTO;
    }

    public Integer delete(Integer id) {
        VideoTagEntity entity = get(id);
        checkOwner(entity.getProfileId());
        videoTagRepository.delete(entity);
        return 1;
    }
    private VideoTagEntity checkOwner(Integer id) {
        VideoTagEntity entity = get(id);
        if (!entity.getProfileId().equals(SpringSecurityUtil.getProfileId())) {
            throw new AppBadRequestException("This User Is Not Owner");
        }
        return entity;
    }
    public VideoTagEntity get(Integer id){
        Optional<VideoTagEntity> optional = videoTagRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadRequestException("tag not found");
        }
        return optional.get();
    }

    public List<VideoTagDTO> list(String videoId) {
        List<VideoTagEntity> entities = videoTagRepository.findAllByVideoId(videoId);
        List<VideoTagDTO> list = new LinkedList<>();
        for (VideoTagEntity entity : entities){
            VideoTagDTO dto =entityToVideoTagDTO(entity);
            list.add(dto);
        }
        return list;
    }
}
