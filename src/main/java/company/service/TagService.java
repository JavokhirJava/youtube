package company.service;

import com.dto.TagRequestDTO;
import company.dto.tag.TagDTO;
import company.entity.TagEntity;
import company.exps.AppBadRequestException;
import company.exps.ItemNotFoundException;
import company.repository.TagRepository;
import company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

    @Service
    public class TagService {
        @Autowired
        private TagRepository tagRepository;

        public TagDTO create(TagRequestDTO dto) {
            TagEntity entity = new TagEntity();
            TagDTO tagDTO = new TagDTO();
            entity.setName(dto.getName());
            tagRepository.save(entity);
            tagDTO.setId(entity.getId());
            tagDTO.setName(dto.getName());
            return tagDTO;
        }

        public TagDTO update(Integer id, TagRequestDTO dto) {
            TagDTO tagDTO = new TagDTO();
            Optional<TagEntity> optional = tagRepository.findById(id);
            if (optional.isEmpty()) {
                throw new AppBadRequestException("Tag is empty");
            }
            TagEntity entity = optional.get();
            entity.setName(dto.getName());
            tagRepository.save(entity);
            tagDTO.setName(entity.getName());
            tagDTO.setId(entity.getId());
            return tagDTO;
        }

        public boolean delete(Integer id) {
            Optional<TagEntity> byId = tagRepository.findById(id);
            if (byId.isEmpty()) {
                throw new ItemNotFoundException("Tag not found");
            }
            tagRepository.updateTag(id, SpringSecurityUtil.getProfileId());
            return true;
        }


        public List<TagDTO> getList() {
            List<TagDTO> dtoList = new LinkedList<>();
            Iterable<TagEntity> tagList = tagRepository.findAll();
            for (TagEntity entity : tagList) {
                TagDTO dto = new TagDTO();
                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dtoList.add(dto);
            }
            return dtoList;
        }


    }
