package company.controller;

import company.dto.channel.ChannelDTO;
import company.dto.channel.ChannelResponseDTO;
import company.service.ChannelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;
    @PostMapping("/user")
    public ResponseEntity<ChannelResponseDTO> create(@RequestBody @Valid ChannelDTO dto) {
        return ResponseEntity.ok(channelService.create(dto));
    }
}
