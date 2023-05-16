package company.service;

import company.dto.category.CategoryDTO;
import company.entity.CategoryEntity;
import company.exps.AppBadRequestException;
import company.repository.CategoryRepository;
import company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryDTO dto) {
         CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public CategoryDTO update(Integer id, CategoryDTO dto) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Region is empty");
        }
        CategoryEntity entity = optional.get();
        entity.setName(dto.getName());
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean deleteById(Integer id) {
        CategoryEntity entity = get(id);
        entity.setId(SpringSecurityUtil.getProfileId());
        categoryRepository.save(entity);
        return true;
    }

    public CategoryEntity get(Integer getById) {
        Optional<CategoryEntity> byId = categoryRepository.findById(getById);
        CategoryEntity entity = byId.get();
        return entity;
    }

    public List<CategoryDTO> getList() {
        List<CategoryDTO> dtoList = new LinkedList<>();
        Iterable<CategoryEntity> regionList = categoryRepository.findAll();
        for (CategoryEntity entity : regionList) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

}
