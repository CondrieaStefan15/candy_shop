package ro.deloittedigital.bootcampbackend.boundry.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.deloittedigital.bootcampbackend.boundry.dto.CategoryDTO;
import ro.deloittedigital.bootcampbackend.entity.model.Category;
import ro.deloittedigital.bootcampbackend.services.interfaces.CategoryService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;


    @PostMapping()
    ResponseEntity<?> createCategory(@RequestBody CategoryDTO category) {

        Category categoryDTO = categoryService.createCategory(category);

        return ResponseEntity.ok(categoryDTO);

    }

    @PatchMapping("/{categoryId}")
    ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO category) {

        Category categoryDTO = categoryService.updateCategory(categoryId, category);

        return ResponseEntity.ok(categoryDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable(name = "id") Long categoryId) {

        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);

        return ResponseEntity.ok().body(categoryDTO);

    }

    @GetMapping("")
    ResponseEntity<?> getAllCategories() {

        List<CategoryDTO> categories = categoryService.getAll();

        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

}