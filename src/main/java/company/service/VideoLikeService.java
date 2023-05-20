package company.service;

import company.dto.VideoLikeDTO;
import company.entity.VideoLikeEntity;
import company.enums.Like;
import company.exps.AppBadRequestException;
import company.repository.VideoLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class VideoLikeService {
    @Autowired
    private VideoLikeRepository videoLikeRepository;

    public VideoLikeDTO create(VideoLikeDTO dto) {
        VideoLikeEntity exists = get(dto);
        if (exists != null) {
            updateStatus(dto);
            throw new AppBadRequestException("video like exists");
        }
        VideoLikeEntity entity = new VideoLikeEntity();
        entity.setVideoId(dto.getVideoId());
        entity.setProfileId(dto.getProfileId());
        entity.setType(dto.getType());
        videoLikeRepository.save(entity);
        return dto;
    }

    public void updateStatus(VideoLikeDTO dto) {
        VideoLikeEntity entity1 = get(dto);

        if (entity1.getType().equals(dto.getType())) {
            delete(dto);
            return;
        } else if (entity1.getType().equals(Like.LIKE)) {
            dto.setType(Like.DISLIKE);
        } else if (entity1.getType().equals(Like.DISLIKE)) {
            dto.setType(Like.LIKE);
        }
        entity1.setType(dto.getType());
        videoLikeRepository.save(entity1);

    }

    public VideoLikeEntity get(VideoLikeDTO dto) {
        return videoLikeRepository.findByProfileIdAndVideoId(dto.getProfileId(),
                dto.getVideoId());
    }

    public void delete(VideoLikeDTO dto) {
        videoLikeRepository.deleted(dto.getProfileId(), dto.getVideoId());
    }

    public List<VideoLikeDTO> getLikedVideoList(Integer profileId) {
        List<VideoLikeEntity> entities = videoLikeRepository.findAllByProfileId(profileId);
        List<VideoLikeDTO> list = new LinkedList<>();
        for (VideoLikeEntity entity : entities) {
            VideoLikeDTO dto = entityToVideoLikeDTO(entity);
            list.add(dto);
        }

        return null;
    }

    private VideoLikeDTO entityToVideoLikeDTO(VideoLikeEntity entity) {
        VideoLikeDTO videoLikeDTO = new VideoLikeDTO();
        videoLikeDTO.setType(entity.getType());
        videoLikeDTO.setVideoId(entity.getVideoId());
        videoLikeDTO.setCreatedDate(entity.getCreatedDate());
        return videoLikeDTO;
    }

}
