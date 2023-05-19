package company.repository;

import company.dto.channel.ChannelDTO;
import company.entity.ChannelEntity;
import company.enums.GeneralStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends CrudRepository<ChannelEntity, String>,
        PagingAndSortingRepository<ChannelEntity, String> {
    Optional<ChannelEntity> findByName(String name);

    @Modifying
    @Transactional
    @Query("update ChannelEntity  set name =:name , description=:description where id=:id")
    int update(@Param("id") String id,
               @Param("name") String name,
               @Param("description") String description);

    @Modifying
    @Transactional
    @Query("update ChannelEntity  set photoId =:photoId  where id=:id")
    int updatePhoto(@Param("id") String id,
                    @Param("photoId") String photoId);

    @Modifying
    @Transactional
    @Query("update ChannelEntity  set bannerId =:bannerId  where id=:id")
    int updateBanner(@Param("id") String id,
                     @Param("bannerId") String bannerId);

    @Modifying
    @Transactional
    @Query("update ChannelEntity  set status =:status  where id=:id")
    int changeStatus(@Param("id") String id,
                     @Param("status") GeneralStatus status);
    List<ChannelEntity> findAllByProfileId(Integer id);
}
