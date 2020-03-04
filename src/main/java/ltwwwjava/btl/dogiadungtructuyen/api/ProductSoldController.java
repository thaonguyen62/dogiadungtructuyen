package ltwwwjava.btl.dogiadungtructuyen.api;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.ProductSold;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductSoldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductSoldController {


    @Autowired
    private ProductSoldRepository productSoldRepository;

    @GetMapping("/productsold")
    public List<ProductSold> getAllCustomer(){
        return productSoldRepository.findAll();
    }

    @GetMapping("productsold/{id}")
    public ResponseEntity<ProductSold> getProductSoldById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        ProductSold productSold = productSoldRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
        return ResponseEntity.ok().body(productSold);
    }

    @PostMapping("/productsold")
    public ProductSold createCustomer(@RequestBody ProductSold customer){
        return productSoldRepository.save(customer);
    }

    @PutMapping("/productsold/{id}")
    public ResponseEntity<ProductSold> updateCustomer(@PathVariable(value = "id") String id, @Valid @RequestBody ProductSold cus){
        ProductSold customer = new ProductSold();
        customer.setId(cus.getId());
        customer.setProduct(cus.getProduct());
        customer.setQuanity(cus.getQuanity());
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/productsold/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        ProductSold customer = productSoldRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id: " + id));
        productSoldRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
