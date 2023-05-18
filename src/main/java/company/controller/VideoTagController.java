package company.controller;

import company.dto.videoTag.VideoTagDTO;
import company.dto.videoTag.VideoTagRequestDTO;
import company.service.VideoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/video-tag")
public class VideoTagController {
    @Autowired
    private VideoTagService videoTagService;

    @PostMapping("/user")
    public ResponseEntity<VideoTagDTO> create(@RequestBody VideoTagRequestDTO dto) {
        return ResponseEntity.ok(videoTagService.create(dto));
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(videoTagService.delete(id));
    }

    @GetMapping("/public/list/{videoId}")
    public ResponseEntity<List<VideoTagDTO>> list(@PathVariable("videoId") String videoId) {
        return ResponseEntity.ok(videoTagService.list(videoId));
    }
}
