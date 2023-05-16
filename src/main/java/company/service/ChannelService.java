package company.service;

import company.dto.channel.ChannelDTO;
import company.dto.channel.ChannelResponseDTO;
import company.entity.ChannelEntity;
import company.exps.AppBadRequestException;
import company.repository.ChannelRepository;
import company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ProfileService profileService;

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

    private void isValidChannel(ChannelDTO dto) {
        if (channelRepository.findByName(dto.getName()).isPresent()) {
            throw new AppBadRequestException("channel already exists");
        }
    }
}
