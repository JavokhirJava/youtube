package company.repository;

import company.entity.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
@Transactional
public interface CategoryRepository extends CrudRepository<CategoryEntity,  Integer> {

}
