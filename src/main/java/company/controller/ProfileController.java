package company.controller;

import company.dto.ChangeDTO;
import company.dto.ChangeEmailDTO;
import company.dto.ProfileDTO;
import company.enums.ProfileRole;
import company.service.ProfileService;
import company.util.JwtUtil;
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
        return ResponseEntity.ok(profileService.create(profileDTO));//todo
    }

    @PutMapping("/update/attach")
    public ResponseEntity<?> attachUpdate(@RequestParam("id") String id) {
        return ResponseEntity.ok(profileService.attachUpdate(id));
    }
    @PutMapping("/private/admin/update-email")
    public ResponseEntity<Boolean> changeEmail(@Valid @RequestBody ChangeEmailDTO dto,
                                               HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ROLE_ADMIN);
        Integer prtId = (Integer) request.getAttribute("id");
        Boolean update = profileService.updateEmail(dto, prtId);
        return ResponseEntity.ok(update);
    }
    @GetMapping("/private/admin/profile-Detail")
    public ResponseEntity<Page<ProfileDTO>> getAllProfileDetails(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "2") int size,
                                                  HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(profileService.getProfileDetail(page, size));
    }
    @PostMapping("/changePassword")
    public ResponseEntity<ChangeDTO> changePassword(@Valid @RequestBody ChangeDTO dto) {
        ChangeDTO response = profileService.changePassword(dto);
        return ResponseEntity.ok(response);
    }
}
