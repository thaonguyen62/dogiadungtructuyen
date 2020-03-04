package ltwwwjava.btl.dogiadungtructuyen.api;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Order;
import ltwwwjava.btl.dogiadungtructuyen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public List<Order> getAllCategories() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getCategoryById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Order category = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
        return ResponseEntity.ok().body(category);
    }
    @PostMapping("/orders")
    public Order createProduct(@RequestBody Order category){
        return orderRepository.save(category);
    }
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateProduct(@PathVariable(value = "id") String id, @Valid @RequestBody Order category) throws ResourceNotFoundException{
        Order category1 = orderRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Category not found for this id: " + id));
        category1.setBillDate(category.getBillDate());
        category1.setCustomer(category.getCustomer());
        category1.setId(category.getId());
        category1.setProducts(category1.getProducts());
        category1.setStatus(category1.getStatus());
        category1.setTax(category1.getTax());
        category1.setTotalPrice(category1.getTotalPrice());
        final Order c = orderRepository.save(category1);
        return ResponseEntity.ok(c);

    }
    @DeleteMapping("/orders/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Order category = orderRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
        orderRepository.delete(category);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
