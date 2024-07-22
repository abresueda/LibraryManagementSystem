package dev.patika.Library.api;

import dev.patika.Library.business.abstracts.ICategoryService;
import dev.patika.Library.dto.request.category.CategoryRequest;
import dev.patika.Library.dto.response.category.CategoryResponse;
import dev.patika.Library.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> save (@RequestBody CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        Category savedCategory = categoryService.save(category);
        CategoryResponse response = new CategoryResponse(savedCategory.getId(), savedCategory.getName(), savedCategory.getDescription());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> get (@PathVariable int id) {
        Category category = categoryService.get(id);
        CategoryResponse response = new CategoryResponse(category.getId(), category.getName(), category.getDescription());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update (@PathVariable int id, @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.get(id);
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        Category updatedCategory = categoryService.update(category);
        CategoryResponse response = new CategoryResponse(updatedCategory.getId(), updatedCategory.getName(), updatedCategory.getDescription());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable int id) {
        String result = categoryService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Category>> cursor (@RequestParam int page, @RequestParam int pageSize) {
        Page<Category> categories = categoryService.cursor(page, pageSize);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
