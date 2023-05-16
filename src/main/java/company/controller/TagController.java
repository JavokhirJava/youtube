package com.controller;

import com.dto.TagRequestDTO;
import com.dto.jwt.JwtDTO;
import com.dto.tag.TagDTO;
import com.enums.ProfileRole;
import com.exps.MethodNotAllowedException;
import com.service.TagService;
import com.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/public")
    public ResponseEntity<TagDTO> create(@RequestBody TagRequestDTO dto) {
        return ResponseEntity.ok(tagService.create(dto));
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<TagDTO> update(@PathVariable("id") Integer id, @RequestBody TagRequestDTO dto) {
        return ResponseEntity.ok(tagService.update(id, dto));
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(tagService.delete(id));
    }


    @GetMapping(value = "/public/list")
    public ResponseEntity<List<TagDTO>> getList(@RequestHeader("Authorization") String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ROLE_ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        List<TagDTO> response = tagService.getList();
        return ResponseEntity.ok(response);
    }
}
