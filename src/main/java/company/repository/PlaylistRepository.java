package company.repository;

import company.entity.PlaylistEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends CrudRepository<PlaylistEntity, String> {

    Optional<PlaylistEntity> findById(Integer getById);
    List<PlaylistEntity> findAllByProfileIdOrderByOrderNumDesc(Integer profileId);
    List<PlaylistEntity> findAllByChannelIdOrderByOrderNumDesc(String channelId);
}
