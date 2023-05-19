package company.mapper;

import company.dto.attach.AttachDTO;

public interface ProfileMapper {
    Integer getId();
    String getEmail();
    String getName();
    String getSurname();
    AttachDTO getPhoto();
}
