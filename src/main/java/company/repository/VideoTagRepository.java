package company.repository;

import company.entity.VideoTagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VideoTagRepository extends CrudRepository<VideoTagEntity, Integer> {
    Optional<VideoTagEntity> findByTagId(Integer id);
    List<VideoTagEntity> findAllByVideoId(String id);
}
