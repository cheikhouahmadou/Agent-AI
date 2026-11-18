package sn.groupeisi.customerservice.mcp;

import org.springframework.ai.mcp.annotation.McpArg;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sn.groupeisi.customerservice.domain.Customer;
import sn.groupeisi.customerservice.repository.CustomerRepository;

import java.util.List;

@Controller
public class CustomerMCPService {
    private  final CustomerRepository customerRepository;

    public CustomerMCPService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @McpTool(description = "Get All customers")
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @McpTool(description = "Get  customer by id")
    public Customer getCustomerById(@McpArg(description = "The customer id") Long id) {
        return customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found!"));
    }

    @McpTool(description = "save a new customer")
    public Customer save(@McpArg(description = "save new customer (name, email)") Customer customer) {
        return customerRepository.save(customer);
    }

}
