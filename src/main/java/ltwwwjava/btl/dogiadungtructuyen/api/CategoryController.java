package ltwwwjava.btl.dogiadungtructuyen.api;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
        return ResponseEntity.ok().body(category);
    }
    @PostMapping("/categories")
    public Category createProduct(@RequestBody Category category){
        return categoryRepository.save(category);
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateProduct(@PathVariable(value = "id") String id, @Valid @RequestBody Category category) throws ResourceNotFoundException{
        Category category1 = categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Category not found for this id: " + id));
        category1.setStatus(category.getStatus());
        category1.setName(category.getName());
        category1.setLink(category.getLink());
        category1.setId(category.getId());
        final Category c = categoryRepository.save(category1);
        return ResponseEntity.ok(c);

    }

    @DeleteMapping("/categories/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
        categoryRepository.delete(category);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
