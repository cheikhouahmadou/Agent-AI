package sn.groupeisi.customerservice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.groupeisi.customerservice.domain.Customer;
import sn.groupeisi.customerservice.repository.CustomerRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestService {
    private  final CustomerRepository customerRepository;

    public CustomerRestService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found!"));
    }

    @PostMapping("/customers")
    public Customer save(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exceptionHandler(Exception e) {
        return e.getMessage();
    }
}
