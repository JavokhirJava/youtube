package com.repository;

import com.entity.TagEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TagRepository extends CrudRepository<TagEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update TagEntity set id =:prtId where id =:id")
    int updateTag(@Param("id") Integer id, @Param("prtId") Integer prtId);

}
