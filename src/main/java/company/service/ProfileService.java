package company.service;

import company.dto.ProfileDTO;
import company.entity.ProfileEntity;
import company.enums.GeneralStatus;
import company.enums.ProfileRole;
import company.exps.AppBadRequestException;
import company.exps.ItemNotFoundException;
import company.repository.ProfileRepository;
import company.util.MD5Util;
import company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AttachService attachService;
    public ProfileEntity getProfile(Integer profileId) {
        Optional<ProfileEntity> optional = profileRepository.findById(profileId);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("profile not found");
        }
        ProfileEntity entity = optional.get();
        entity.setRole(ProfileRole.ROLE_OWNER);
        return entity;
    }
    public ProfileEntity toEntity(ProfileDTO profileDTO) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(profileDTO.getName());
        entity.setSurname(profileDTO.getSurname());
        entity.setPassword(MD5Util.encode(profileDTO.getPassword()));
//        entity.setRole(profileDTO.getRole());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setPhoto(attachService.get(profileDTO.getPhotoId()));
        return entity;
    }


    public ProfileDTO create(ProfileDTO dto) {
        if (profileRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new AppBadRequestException("This email is already registered");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword()));
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);
        entity.setPrtId(SpringSecurityUtil.getProfileId());
        profileRepository.save(entity);
        dto.setPassword(null);
        dto.setId(entity.getId());
        return dto;
    }
}
