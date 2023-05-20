package company.controller;

import company.dto.playlist.PlayListInfoDTO;
import company.dto.playlist.PlaylistDTO;
import company.service.PlaylistService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;
//    private  static final Logger LOGGER = Logger.getLogger(PlaylistController.class);
    @PostMapping("/user/create")
    public ResponseEntity<PlaylistDTO> create(@RequestBody PlaylistDTO dto) {
//        LOGGER.info("");
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

    @GetMapping("/get_list_user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PlayListInfoDTO>> getListWithUserId(@PathVariable("id")Integer profileId) {
        List<PlayListInfoDTO> response = playlistService.getListWithUser(profileId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/private/get_user_playlist")
    public ResponseEntity<List<PlayListInfoDTO>> getUserPlaylist() {
        List<PlayListInfoDTO> response = playlistService.getUserPlayList();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/public/get_channel_playlist/{id}")
    public ResponseEntity<List<PlayListInfoDTO>> getChannelPlaylist(@PathVariable("id")String channelId) {
        List<PlayListInfoDTO> response = playlistService.getChannelPlayList(channelId);
        return ResponseEntity.ok(response);
    }
}
