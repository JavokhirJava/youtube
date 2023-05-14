package com.repository;

import com.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> findByEmailAndPasswordAndVisible(String email,String password, boolean visible);
}
