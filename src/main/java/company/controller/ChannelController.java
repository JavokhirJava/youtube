package company.controller;

import company.dto.channel.ChannelDTO;
import company.dto.channel.ChannelRequestDTO;
import company.dto.channel.ChannelResponseDTO;
import company.dto.profile.ChangeDTO;
import company.service.ChannelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ChannelResponseDTO> create(@RequestBody @Valid ChannelDTO dto) {
        return ResponseEntity.ok(channelService.create(dto));
    }
    @PutMapping("/user/update")
    public ResponseEntity<Integer> update(@RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.update(dto));
    }
    @PutMapping("/user/photo")
    public ResponseEntity<Integer> updatePhoto(@RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.updatePhoto(dto));
    }
    @PutMapping("/user/banner")
    public ResponseEntity<Integer> updateBanner(@RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.updateBanner(dto));
    }
    @GetMapping("/adm/pagination")
    public ResponseEntity<Page<ChannelResponseDTO>> getByCategoryPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                                              @RequestParam(value = "size", defaultValue = "3") int size) {
        Page<ChannelResponseDTO> pagination = channelService.pagination(size, page);
        return ResponseEntity.ok(pagination);
    }
    @GetMapping("/public/get_by_id")
    public ResponseEntity<ChannelDTO> getById(@RequestParam(value = "id") String id){
        ChannelDTO dto= channelService.getById(id);
        return ResponseEntity.ok(dto);
    }
    @PutMapping("/adm-user/change_status")
    public ResponseEntity<Integer> changeStatus(@RequestBody ChannelRequestDTO dto) {
        return ResponseEntity.ok(channelService.changeStatus(dto));
    }
    @GetMapping("private/channel_list")
    public ResponseEntity<List<ChannelDTO>> getChannelList(){
        List<ChannelDTO> dto= channelService.channelList();
        return ResponseEntity.ok(dto);
    }
}
