package company.repository;

import company.entity.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentEntity, Integer>,
        PagingAndSortingRepository<CommentEntity,Integer> {
    @Modifying
    @Transactional
    @Query("update CommentEntity set content=:content where id=:id")
    Integer update(@Param("id") Integer id,
                   @Param("content") String content);
    List<CommentEntity> findAllByProfileIdOrderByCreatedDate(Integer profileId);
    List<CommentEntity> findAllByVideoIdOrderByCreatedDate(String videoId);
    List<CommentEntity> findAllByReplyId(Integer commentId);
}
