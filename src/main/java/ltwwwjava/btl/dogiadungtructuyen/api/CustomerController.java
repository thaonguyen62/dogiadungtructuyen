package ltwwwjava.btl.dogiadungtructuyen.api;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Customer;
import ltwwwjava.btl.dogiadungtructuyen.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") String id) throws ResourceNotFoundException{
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") String id, @Valid @RequestBody Customer cus){
        Customer customer = new Customer();
        customer.setAddress(cus.getAddress());
        customer.setBirth(cus.getBirth());
        customer.setId(cus.getId());
        customer.setMail(cus.getMail());
        customer.setName(cus.getName());
        customer.setPhone(cus.getPhone());
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id: " + id));
        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
