package company.controller;

import company.dto.VideoLikeDTO;
import company.enums.Like;
import company.service.VideoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/video_like")
public class VideoLikeController {
    @Autowired
    private VideoLikeService videoLikeService;

    @PostMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<VideoLikeDTO> create(@RequestBody VideoLikeDTO dto) {
        return ResponseEntity.ok(videoLikeService.create(dto));
    }

    @PutMapping("/user/update")
    public ResponseEntity<?> update(@RequestBody VideoLikeDTO dto) {
        videoLikeService.updateStatus(dto);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/public/delete")
    public ResponseEntity<?> delete(@RequestBody VideoLikeDTO dto) {
        videoLikeService.delete(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/getLikedVideoList/{profileId}")
    public ResponseEntity<List<VideoLikeDTO>> getLikedVideoList(@PathVariable("profileId") Integer profileId) {
        return ResponseEntity.ok(videoLikeService.getLikedVideoList(profileId));
    }

}
