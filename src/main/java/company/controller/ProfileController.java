package company.controller;

import company.dto.ChangeDTO;
import company.dto.ChangeEmailDTO;
import company.dto.ProfileDTO;
import company.enums.ProfileRole;
import company.service.ProfileService;
import company.util.JwtUtil;
import company.util.SpringSecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static company.enums.ProfileRole.ROLE_MODERATOR;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @PostMapping( "/adm-moder/create")
    public ResponseEntity<ProfileDTO> save(@RequestBody @Valid ProfileDTO profileDTO) {
        return ResponseEntity.ok(profileService.create(profileDTO));
    }
}
