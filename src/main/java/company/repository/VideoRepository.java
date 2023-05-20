package company.repository;

import company.dto.video.VideShortInfoDTO;
import company.entity.ProfileEntity;
import company.entity.VideoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends CrudRepository<VideoEntity, String>, PagingAndSortingRepository<VideoEntity, String> {

    @Transactional
    @Modifying
    @Query("update VideoEntity as v  set v.viewCount = v.viewCount+1  where v.id =:id")
    int viewCount(@Param("id") String  id);

   List<VideoEntity> findByTitle(String title);
   Page<VideoEntity> findByCategoryId(Integer id,Pageable pageable);

}
