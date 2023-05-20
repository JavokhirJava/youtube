package company.service;

import company.dto.playlist.PlayListInfoDTO;
import company.dto.playlist.PlaylistDTO;
import company.entity.PlaylistEntity;
import company.exps.AppBadRequestException;
import company.repository.PlaylistRepository;
import company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ChannelService channelService;

    public PlaylistDTO create(PlaylistDTO dto) {
        PlaylistEntity entity = new PlaylistEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setProfileId(SpringSecurityUtil.getProfileId());
        entity.setChannelId(dto.getChannelId());
        entity.setOrderNum(dto.getOrderNum());
        playlistRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public PlaylistDTO update(Integer id, PlaylistDTO dto) {
        Optional<PlaylistEntity> optional = playlistRepository.findById(String.valueOf(id));
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Region is empty");
        }
        PlaylistEntity entity = optional.get();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        playlistRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean deleteById(Integer id) {
        PlaylistEntity entity = get(id);
        entity.setId(SpringSecurityUtil.getProfileId());
        playlistRepository.save(entity);
        return true;
    }

    public PlaylistEntity get(Integer getById) {
        Optional<PlaylistEntity> byId = playlistRepository.findById(getById);
        PlaylistEntity entity = byId.get();
        return entity;
    }

    public List<PlaylistDTO> getList() {
        List<PlaylistDTO> dtoList = new LinkedList<>();
        Iterable<PlaylistEntity> regionList = playlistRepository.findAll();
        for (PlaylistEntity entity : regionList) {
            PlaylistDTO dto = new PlaylistDTO();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<PlayListInfoDTO> getListWithUser(Integer profileId) {
        List<PlayListInfoDTO> dtoList = new LinkedList<>();
        List<PlaylistEntity> list = playlistRepository.findAllByProfileIdOrderByOrderNumDesc(profileId);
        for (PlaylistEntity entity : list) {
            PlayListInfoDTO dto = new PlayListInfoDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setProfile(profileService.getProfile(profileId));
            dto.setChannel(channelService.getChannelPhoto(entity.getChannelId()));
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<PlayListInfoDTO> getUserPlayList() {
        List<PlayListInfoDTO> dtoList = new LinkedList<>();
        List<PlaylistEntity> list = playlistRepository.findAllByProfileIdOrderByOrderNumDesc(SpringSecurityUtil.getProfileId());
        for (PlaylistEntity entity : list) {
            PlayListInfoDTO dto = new PlayListInfoDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setProfile(profileService.getProfile(SpringSecurityUtil.getProfileId()));
            dto.setChannel(channelService.getChannelPhoto(entity.getChannelId()));
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<PlayListInfoDTO> getChannelPlayList(String channelId) {
        List<PlayListInfoDTO> dtoList = new LinkedList<>();
        List<PlaylistEntity> list = playlistRepository.findAllByChannelIdOrderByOrderNumDesc(channelId);
        for (PlaylistEntity entity : list) {
            PlayListInfoDTO dto = new PlayListInfoDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setChannel(channelService.getChannelPhoto(entity.getChannelId()));
            dtoList.add(dto);
        }
        return dtoList;
    }
}
