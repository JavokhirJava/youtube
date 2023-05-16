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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static company.enums.ProfileRole.ROLE_MODERATOR;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping({ "/admin/create"})
    public ResponseEntity<ProfileDTO> save(@Valid @RequestBody ProfileDTO profileDTO,
                                           HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ROLE_ADMIN,ROLE_MODERATOR);
        Integer prtId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(profileService.create(profileDTO, prtId));
    }


}
