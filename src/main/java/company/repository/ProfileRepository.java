package company.repository;

import company.dto.ProfileDTO;
import company.entity.ProfileEntity;
import company.mapper.ProfileMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> , PagingAndSortingRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByEmail(String email);
    ProfileEntity findByPassword(String encode);
    Optional<ProfileEntity> findByEmailAndPasswordAndVisible(String email, String password, boolean visible);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set email = ?1 , status = ?2 where id = ?3")
    int changeEmail(String newEmail, String status, Integer id);
    @Query(value = "Select p.id,p.email,p.role from profile as p where  id = :id",nativeQuery = true)
    ProfileMapper getProfileById(@Param("id")Integer id);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set attachId = ?1  where id = ?2")
    int attachUpdate(String id, Integer userId);

//    ProfileMapper getProfileById(Integer profileId);


   @Transactional
    @Modifying
    @Query("update ProfileEntity set name = ?1, surname = ?2   where id = ?3")
    int updateUserById(String name, String surname, Integer currentUserId);


    @Transactional
    @Modifying
    @Query("update ProfileEntity set password = ?2 where id = ?1")
    int updatePassword(Integer id, String newPassword);
}
