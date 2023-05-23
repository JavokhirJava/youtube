package company.controller;

import company.dto.VideoLikeDTO;
import company.service.VideoLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/video_like")
public class VideoLikeController {
    @Autowired
    private VideoLikeService videoLikeService;

    @PostMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<VideoLikeDTO> create(@RequestBody VideoLikeDTO dto) {
        log.info("User's emotion status on video  : ->" + dto);
        return ResponseEntity.ok(videoLikeService.create(dto));
    }

    @PutMapping("/user/update")
    public ResponseEntity<?> update(@RequestBody VideoLikeDTO dto) {
        log.info("User update  emotion status on video  : ->" + dto);
        videoLikeService.updateStatus(dto);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/public/delete")
    public ResponseEntity<?> delete(@RequestBody VideoLikeDTO dto) {
        log.info("User delete  emotion status on video  : ->" + dto);
        videoLikeService.delete(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/getLikedVideoList/{profileId}")
    public ResponseEntity<List<VideoLikeDTO>> getLikedVideoList(@PathVariable("profileId") Integer profileId) {
        return ResponseEntity.ok(videoLikeService.getLikedVideoList(profileId));
    }

}
