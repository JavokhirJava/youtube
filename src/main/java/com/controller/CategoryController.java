package com.controller;

import com.dto.category.CategoryDTO;
import com.dto.jwt.JwtDTO;
import com.enums.ProfileRole;
import com.exps.MethodNotAllowedException;
import com.service.CategoryService;
import com.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("adm/private/create")
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping("adm/update/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Integer id,
                                              @RequestBody CategoryDTO categoryDto) {
        return ResponseEntity.ok(categoryService.update(id, categoryDto));
    }

    @DeleteMapping("adm/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(categoryService.deleteById(id));
    }

    @GetMapping(value = "/public/list")
    public ResponseEntity<List<CategoryDTO>> getList() {
        List<CategoryDTO> response = categoryService.getList();
        return ResponseEntity.ok(response);
    }
}
