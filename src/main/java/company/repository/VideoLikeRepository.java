package company.repository;

import company.entity.VideoLikeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VideoLikeRepository extends CrudRepository<VideoLikeEntity, Integer> {
    VideoLikeEntity findByProfileIdAndVideoId(Integer profileId, String videoId);

    @Transactional
    @Modifying
    @Query("delete from VideoLikeEntity a where a.profileId = ?1 and a.videoId = ?2")
    void deleted(Integer profileId, String videoId);

    List<VideoLikeEntity> findAllByProfileId(Integer profileId);
}
