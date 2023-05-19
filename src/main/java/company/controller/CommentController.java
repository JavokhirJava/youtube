package company.controller;

import company.dto.comment.CommentInfoDTO;
import company.dto.comment.CommentResponseDTO;
import company.dto.comment.CommentDTO;
import company.dto.comment.CommentRequestDTO;
import company.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/user")
    public ResponseEntity<CommentDTO> create(@RequestBody  CommentRequestDTO dto) {
        return ResponseEntity.ok(commentService.create(dto));
    }
    @PutMapping("/user/update")
    public ResponseEntity<Integer> update(@RequestBody CommentRequestDTO dto) {
        return ResponseEntity.ok(commentService.update(dto));
    }
    @DeleteMapping("/adm-user/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(commentService.delete(id));
    }
    @GetMapping("/adm/pagination")
    public ResponseEntity<Page<CommentResponseDTO>> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                  @RequestParam(value = "size", defaultValue = "3") int size) {
        Page<CommentResponseDTO> pagination = commentService.pagination(size, page);
        return ResponseEntity.ok(pagination);
    }
    @GetMapping("/adm/list-profileId")
    public ResponseEntity<List<CommentResponseDTO>> getListByProfileId(@RequestParam("profileId") int profileId) {
        List<CommentResponseDTO> list = commentService.listByProfileId(profileId);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/user/list-jwt")
    public ResponseEntity<List<CommentResponseDTO>> getListByJWT() {
        List<CommentResponseDTO> list = commentService.listByJWT();
        return ResponseEntity.ok(list);
    }
    @GetMapping("public/list-videoId")
    public ResponseEntity<List<CommentInfoDTO>> getVideoComments(@RequestParam("videoId") String videoId) {
        List<CommentInfoDTO> list = commentService.listByVideoId(videoId);
        return ResponseEntity.ok(list);
    }
    @GetMapping("public/list-replied")
    public ResponseEntity<List<CommentInfoDTO>> getRepliedComments(@RequestParam("commentId") Integer commentId) {
        List<CommentInfoDTO> list = commentService.listByRepliedComment(commentId);
        return ResponseEntity.ok(list);
    }
}
