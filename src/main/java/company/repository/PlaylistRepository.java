package company.repository;

import company.entity.PlaylistEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlaylistRepository extends CrudRepository<PlaylistEntity, String> {

    Optional<PlaylistEntity> findById(Integer getById);
}
