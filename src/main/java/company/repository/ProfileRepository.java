package company.repository;

import company.entity.ProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByEmail(String email);


    Optional<ProfileEntity> findByEmailAndPasswordAndVisible(String email, String password, boolean visible);

//    ProfileEntity findByPassword(String password);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set email = ?1 , status = ?2 where id = ?3")
    int changeEmail(String newEmail, String status, Integer id);

}
