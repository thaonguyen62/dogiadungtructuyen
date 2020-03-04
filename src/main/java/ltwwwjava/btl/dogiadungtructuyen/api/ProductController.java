package ltwwwjava.btl.dogiadungtructuyen.api;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return  productRepository.findAll();
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") String id) throws  ResourceNotFoundException{
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
        return ResponseEntity.ok().body(product);
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") String id, @Valid @RequestBody Product product) throws ResourceNotFoundException{
        Product product1 = productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
        //product1.setCategories(product.getCategories());
        product1.setCategory(product.getCategory());
        product1.setDescription(product.getDescription());
        product1.setFileName(product.getFileName());
        product1.setId(product.getId());
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        final Product p = productRepository.save(product1);
        return ResponseEntity.ok(p);

    }
    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Product product1 = productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
        productRepository.delete(product1);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
