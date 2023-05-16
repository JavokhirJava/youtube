package company.mapper;

import company.enums.ProfileRole;

public interface ProfileMapper {
    Integer getId();
    String getEmail();
    ProfileRole getRole();
}
