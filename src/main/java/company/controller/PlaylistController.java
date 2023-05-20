package company.controller;

import company.dto.playlist.PlaylistDTO;
import company.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/playlist")
public class PlaylistController {
        @Autowired
        private PlaylistService playlistService;

        @PostMapping("/user/create")
        public ResponseEntity<PlaylistDTO> create(@RequestBody PlaylistDTO dto) {
            return ResponseEntity.ok(playlistService.create(dto));
        }

        @PutMapping("/user/update/{id}")
        public ResponseEntity<PlaylistDTO> update(@PathVariable("id") Integer id,
                                                  @RequestBody PlaylistDTO playlistDTO) {
            return ResponseEntity.ok(playlistService.update(id, playlistDTO));
        }

        @DeleteMapping("/adm/delete/{id}")
        public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
            return ResponseEntity.ok(playlistService.deleteById(id));
        }

        @GetMapping(value = "/public/list")
        public ResponseEntity<List<PlaylistDTO>> getList() {
            List<PlaylistDTO> response = playlistService.getList();
            return ResponseEntity.ok(response);
        }
    }
