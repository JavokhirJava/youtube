package company.controller;

import company.dto.profile.ChangeDTO;
import company.dto.profile.ChangeEmailDTO;
import company.dto.profile.ProfileDTO;
import company.service.ProfileService;
import company.util.JwtUtil;
import company.util.SpringSecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static company.enums.ProfileRole.ROLE_MODERATOR;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/adm-moder/create")
    public ResponseEntity<ProfileDTO> save(@RequestBody @Valid ProfileDTO profileDTO) {
        return ResponseEntity.ok(profileService.create(profileDTO));
    }

    @PutMapping("/update/attach")
    public ResponseEntity<?> attachUpdate(@RequestParam("id") String id) {
        return ResponseEntity.ok(profileService.attachUpdate(id));
    }
    @PutMapping("/adm/update-email")
    public ResponseEntity<Boolean> changeEmail(@Valid @RequestBody ChangeEmailDTO dto) {
        Boolean update = profileService.updateEmail(dto);
        return ResponseEntity.ok(update);
    }
    @GetMapping("/adm/profile-Detail")
    public ResponseEntity<Page<ProfileDTO>> getAllProfileDetails(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "2") int size) {
        return ResponseEntity.ok(profileService.getProfileDetail(page, size));
    }
    @PostMapping("/changePassword")
    public ResponseEntity<ChangeDTO> changePassword(@Valid @RequestBody ChangeDTO dto) {
        Integer profilId = SpringSecurityUtil.getProfileId();
            ChangeDTO response = profileService.changePassword(dto,profilId);
        return ResponseEntity.ok(response);
    }
}
