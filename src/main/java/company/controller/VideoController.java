package company.controller;

import company.dto.video.VideShortInfoDTO;
import company.dto.video.VideoDTO;
import company.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/user/create")
    public ResponseEntity<VideoDTO> save(@RequestBody @Valid VideoDTO videoDTO) {
        return ResponseEntity.ok(videoService.create(videoDTO));
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<VideoDTO> update(@PathVariable("id") String id,
                                           @RequestBody VideoDTO videoDTO) {
        return ResponseEntity.ok(videoService.update(id, videoDTO));
    }

    @PutMapping("/user/updateStatus/{id}")
    public ResponseEntity<Boolean> updateStatsus(@PathVariable("id") String id) {
        return ResponseEntity.ok(videoService.changeStatus(id));
    }

    @PutMapping("/public/viewCount/{id}")
    public ResponseEntity<Boolean> viewCount(@PathVariable("id") String id) {
        return ResponseEntity.ok(videoService.viewCount(id));
    }

    @GetMapping("/public/search/{title}")
    public ResponseEntity<?> getsearch(@PathVariable("title") String title) {
        return ResponseEntity.ok(videoService.searchTitle(title));
    }

    @PostMapping(value = "/public/pagingCategory/{categoryId}")
    public ResponseEntity<Page<?>> pagingCategory(@RequestParam(value = "page", defaultValue = "2") int page,
                                                  @RequestParam(value = "size", defaultValue = "2") int size,
                                                  @PathVariable Integer  categoryId) {
        Page<VideShortInfoDTO> response = videoService.pagingCategory(categoryId, page, size);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/public/pagingTagId/{tagId}")
    public ResponseEntity<Page<?>> pagingTagId(@RequestParam(value = "page", defaultValue = "2") int page,
                                                  @RequestParam(value = "size", defaultValue = "2") int size,
                                                  @PathVariable Integer  tagId) {
        Page<VideShortInfoDTO> response = videoService.pagingTag(tagId, page, size);
        return ResponseEntity.ok(response);
    }
}
