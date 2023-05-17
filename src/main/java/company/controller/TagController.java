package company.controller;

import company.dto.tag.TagRequestDTO;
import company.dto.tag.TagDTO;
import company.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/public")
    public ResponseEntity<TagDTO> create(@RequestBody TagRequestDTO dto) {
        return ResponseEntity.ok(tagService.create(dto));
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<TagDTO> update(@PathVariable("id") Integer id, @RequestBody TagRequestDTO dto) {
        return ResponseEntity.ok(tagService.update(id, dto));
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(tagService.delete(id));
    }


    @GetMapping(value = "/public/list")
    public ResponseEntity<List<TagDTO>> getList() {

        List<TagDTO> response = tagService.getList();
        return ResponseEntity.ok(response);
    }
}
