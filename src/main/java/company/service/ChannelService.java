package company.service;

import company.dto.channel.ChannelDTO;
import company.dto.channel.ChannelRequestDTO;
import company.dto.channel.ChannelResponseDTO;
import company.entity.ChannelEntity;
import company.exps.AppBadRequestException;
import company.exps.ItemNotFoundException;
import company.repository.ChannelRepository;
import company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AttachService attachService;

    public ChannelResponseDTO create(ChannelDTO dto) {
        isValidChannel(dto);
        ChannelEntity entity = new ChannelEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPhotoId(dto.getPhotoId());
        entity.setBannerId(dto.getBannerId());
        entity.setProfileId(SpringSecurityUtil.getProfileId());
        channelRepository.save(entity);
        return entityToResponseDTO(entity);
    }

    private ChannelResponseDTO entityToResponseDTO(ChannelEntity entity) {
        ChannelResponseDTO dto = new ChannelResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setProfile(profileService.getProfile(entity.getProfileId()));
        dto.setPhotoId(entity.getPhotoId());
        dto.setBannerId(entity.getBannerId());
        return dto;
    }
    private ChannelDTO entityToDTO (ChannelEntity entity) {
        ChannelDTO dto = new ChannelDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setProfileId(entity.getProfileId());
        dto.setPhotoId(entity.getPhotoId());
        dto.setBannerId(entity.getBannerId());
        return dto;
    }
    private void isValidChannel(ChannelDTO dto) {
        if (channelRepository.findByName(dto.getName()).isPresent()) {
            throw new AppBadRequestException("channel already exists");
        }
    }
    public Integer update(ChannelDTO dto) {
        ChannelEntity entity = checkOwner(dto);
        return channelRepository.update(entity.getId(), dto.getName(), dto.getDescription());
    }
    private ChannelEntity checkOwner(ChannelDTO dto) {
        ChannelEntity entity = get(dto.getId());
        if (!entity.getProfileId().equals(SpringSecurityUtil.getProfileId())) {
            throw new AppBadRequestException("This User Is Not Owner");
        }
        return entity;
    }
    private ChannelEntity get(String id) {
        return channelRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException
                        ("Channel Not Found"));
    }
    public Integer updatePhoto(ChannelDTO dto) {
        ChannelEntity entity = checkOwner(dto);
        return channelRepository.updatePhoto(entity.getId(), dto.getPhotoId());
    }
    public Integer updateBanner(ChannelDTO dto) {
        ChannelEntity entity = checkOwner(dto);
        return channelRepository.updateBanner(entity.getId(), dto.getBannerId());
    }
    public Page<ChannelResponseDTO> pagination(int size, int page) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<ChannelEntity> pageObj = channelRepository.findAll(paging);
        Long totalCount = pageObj.getTotalElements();
        List<ChannelEntity> entityList = pageObj.getContent();
        List<ChannelResponseDTO> list = new LinkedList<>();
        for (ChannelEntity entity : entityList) {
            ChannelResponseDTO dto = new ChannelResponseDTO();
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setProfile(profileService.getProfile(entity.getProfileId()));
            dto.setBanner(attachService.getPhotoBanner(entity.getBannerId()));
            dto.setPhoto(attachService.getPhotoBanner(entity.getPhotoId()));
            list.add(dto);
        }
        return new PageImpl<ChannelResponseDTO>(list, paging, totalCount);
    }

    public ChannelDTO getById(String id) {
        ChannelEntity entity = get(id);
        return entityToDTO(entity);
    }

    public Integer changeStatus(ChannelRequestDTO dto) {
     ChannelEntity entity = get(dto.getId());
     return channelRepository.changeStatus(entity.getId(),dto.getStatus());
    }

    public List<ChannelDTO> channelList() {
        List<ChannelEntity> entityList = channelRepository.findAllByProfileId(SpringSecurityUtil.getProfileId());
        if (entityList.isEmpty()){
            throw new ItemNotFoundException("Channel list is empty");
        }
        List<ChannelDTO> dtoList = new LinkedList<>();
        for (ChannelEntity entity : entityList){
            ChannelDTO dto = entityToDTO(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
