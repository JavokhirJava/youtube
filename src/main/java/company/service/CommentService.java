package company.service;

import company.dto.comment.CommentInfoDTO;
import company.dto.comment.CommentResponseDTO;
import company.dto.comment.CommentDTO;
import company.dto.comment.CommentRequestDTO;
import company.entity.CommentEntity;
import company.exps.AppBadRequestException;
import company.exps.ItemNotFoundException;
import company.repository.CommentRepository;
import company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProfileService profileService;

    public CommentDTO create(CommentRequestDTO dto) {
        CommentEntity entity = new CommentEntity();
        entity.setVideoId(dto.getVideoId());
        entity.setReplyId(dto.getReplyId());
        entity.setProfileId(SpringSecurityUtil.getProfileId());
        entity.setContent(dto.getContent());
        commentRepository.save(entity);
        CommentDTO commentDTO = entityToDTO(entity);
        return commentDTO;
    }

    private CommentEntity get(Integer id) {
        Optional<CommentEntity> optional = commentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Comment Not Found");
        }
        return optional.get();
    }

    private CommentDTO entityToDTO(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setProfileId(entity.getProfileId());
        dto.setVideoId(entity.getVideoId());
        dto.setReplyId(entity.getReplyId());
        return dto;
    }

    public Integer update(CommentRequestDTO dto) {
        CommentEntity entity = checkOwner(dto);
        return commentRepository.update(entity.getId(), dto.getContent());
    }

    private CommentEntity checkOwner(CommentRequestDTO dto) {
        CommentEntity entity = get(dto.getId());
        if (!entity.getProfileId().equals(SpringSecurityUtil.getProfileId())) {
            throw new AppBadRequestException("This User Is Not Owner");
        }
        return entity;
    }

    private CommentEntity checkOwnerOrAdmin(Integer id) {
        CommentEntity entity = get(id);
        if (!entity.getProfileId().equals(SpringSecurityUtil.getProfileId())) {
            throw new AppBadRequestException("This User Is Not Owner");
        } else if (!profileService.isAdmin(entity.getProfile().getId())) {
            throw new AppBadRequestException("This Profile Not Admin");
        }
        return entity;
    }

    public Boolean delete(Integer id) {
        CommentEntity entity = checkOwnerOrAdmin(id);
        commentRepository.delete(entity);
        return true;
    }

    public Page<CommentResponseDTO> pagination(int size, int page) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<CommentEntity> pageObj = commentRepository.findAll(paging);
        Long totalCount = pageObj.getTotalElements();
        List<CommentEntity> entityList = pageObj.getContent();
        List<CommentResponseDTO> list = new LinkedList<>();
        for (CommentEntity entity : entityList) {
            CommentResponseDTO dto = entityToCommentResponseDTO(entity);
            list.add(dto);
        }
        return new PageImpl<CommentResponseDTO>(list, paging, totalCount);
    }

    private CommentResponseDTO entityToCommentResponseDTO(CommentEntity entity) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLikeCount(entity.getLikeCount());
        dto.setDislikeCount(entity.getDislikeCount());
//            dto.setVideo(v.getPhotoBanner(entity.getBannerId()));
        return dto;
    }

    private CommentDTO getCommentDTO(Integer id) {
        CommentEntity entity = get(id);
        return entityToDTO(entity);
    }

    public List<CommentResponseDTO> listByProfileId(int profileId) {
        List<CommentEntity> entities = commentRepository.findAllByProfileIdOrderByCreatedDate(profileId);
        List<CommentResponseDTO> list = new LinkedList<>();
        for(CommentEntity entity : entities){
            CommentResponseDTO dto = entityToCommentResponseDTO(entity);
            list.add(dto);
        }
        return list;
    }

    public List<CommentResponseDTO> listByJWT() {
        List<CommentEntity> entities = commentRepository.findAllByProfileIdOrderByCreatedDate(SpringSecurityUtil.getProfileId());
        List<CommentResponseDTO> list = new LinkedList<>();
        for(CommentEntity entity : entities){
            CommentResponseDTO dto = entityToCommentResponseDTO(entity);
            list.add(dto);
        }
        return list;
    }

    public List<CommentInfoDTO> listByVideoId(String videoId) {
        List<CommentEntity> entities = commentRepository.findAllByVideoIdOrderByCreatedDate(videoId);
        List<CommentInfoDTO> list = new LinkedList<>();
        for(CommentEntity entity : entities){
            CommentInfoDTO dto = entityToCommentInfoDTO(entity);
            list.add(dto);
        }
        return list;
    }

    private CommentInfoDTO entityToCommentInfoDTO(CommentEntity entity) {
        CommentInfoDTO dto = new CommentInfoDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setProfile(profileService.getProfile(entity.getProfileId()));
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLikeCount(entity.getLikeCount());
        dto.setDislikeCount(entity.getDislikeCount());
        return dto;
    }

    public List<CommentInfoDTO> listByRepliedComment(Integer commentId) {
        List<CommentEntity> entities = commentRepository.findAllByReplyId(commentId);
        List<CommentInfoDTO> list = new LinkedList<>();
        for(CommentEntity entity : entities){
            CommentInfoDTO dto = entityToCommentInfoDTO(entity);
            list.add(dto);
        }
        return list;
    }
}
