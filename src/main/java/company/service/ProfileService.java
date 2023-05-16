package company.service;

import company.dto.ChangeDTO;
import company.dto.ChangeEmailDTO;
import company.dto.ProfileDTO;
import company.entity.AttachEntity;
import company.entity.ProfileEntity;
import company.enums.GeneralStatus;
import company.enums.ProfileRole;
import company.exps.AppBadRequestException;
import company.exps.ItemNotFoundException;
import company.exps.WrongException;
import company.repository.AttachRepository;
import company.repository.ProfileRepository;
import company.util.MD5Util;
import company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private String attachFolder;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AttachService attachService;
    @Autowired
    private AttachRepository attachRepository;
    public ProfileEntity toEntity(ProfileDTO profileDTO) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(profileDTO.getName());
        entity.setSurname(profileDTO.getSurname());
        entity.setPassword(MD5Util.encode(profileDTO.getPassword()));
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
//    public ProfileDTO getProfile(Integer profileId) {
//        ProfileMapper mapper = profileRepository.getProfileById(profileId);
//        if (mapper==null){
//            throw new ItemNotFoundException("profile not found");
//        }
//        ProfileDTO dto = new ProfileDTO();
//        dto.setId(mapper.getId());
//        dto.setEmail(mapper.getEmail());
//        dto.setRole(mapper.getRole());
//        return dto;
//    }


    public int attachUpdate(String id) {
        Integer userId = SpringSecurityUtil.getProfileId();
        ProfileEntity profile = get(userId);
        AttachEntity attach = attachRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Not found");
        });
        profileRepository.attachUpdate(id,profile.getId());
        File file = new File(attachFolder + attach.getPath() + "/" + id + "." + attach.getExtension());
        if (file.delete()) {
            attachRepository.deleteById(id);
        }
        return profileRepository.attachUpdate(id,userId);
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException
                        ("Profile Not Found"));
    }



    public Boolean updateEmail(ChangeEmailDTO dto,Integer adminId) {
        Optional<ProfileEntity> entity = profileRepository.findByEmail(dto.getOldEmail());
        if (entity == null){
            throw new ItemNotFoundException("Not found");
        }
        ProfileEntity profileEntity = get(dto.getId());
        profileEntity.setEmail(dto.getNewEmail());
        profileEntity.setPrtId(adminId);
        profileRepository.save(profileEntity);
        return true;
    }


    public Page<ProfileDTO> getProfileDetail(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<ProfileEntity> pageObj =  profileRepository.findAll(paging);
        long totalCount = pageObj.getTotalElements();
//id,name,surname,email,main_photo((url)
        List<ProfileEntity> entityList = pageObj.getContent();
        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : entityList) {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setEmail(entity.getEmail());
            dto.setPhotoId(entity.getPhoto().getPath());
            dtoList.add(dto);
        }
        return new PageImpl<ProfileDTO>(dtoList, paging, totalCount);
    }

    public int updateUser(ProfileDTO profileDTO) {
        return profileRepository.updateUserById(profileDTO.getName(),
                profileDTO.getSurname(), SpringSecurityUtil.getProfileId());
    }

    public ChangeDTO changePassword(ChangeDTO dto) {
        ProfileEntity exists = profileRepository.findByPassword(MD5Util.encode(dto.getOldPassword()));
        if (exists == null){
            throw new ItemNotFoundException("Not found");
        }
        int b = profileRepository.updatePassword(exists.getId(),MD5Util.encode(dto.getNewPassword()));
        if (b == 0){
            throw new WrongException("Error");
        }
        return dto;
    }
}
