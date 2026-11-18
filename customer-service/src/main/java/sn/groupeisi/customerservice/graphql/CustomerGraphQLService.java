package sn.groupeisi.customerservice.graphql;

import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sn.groupeisi.customerservice.domain.Customer;
import sn.groupeisi.customerservice.repository.CustomerRepository;

import java.util.List;

@Controller
public class CustomerGraphQLService {
    private  final CustomerRepository customerRepository;

    public CustomerGraphQLService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @QueryMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @QueryMapping
    public Customer getCustomerById(@Argument Long id) {
        return customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found!"));
    }

    @MutationMapping
    public Customer saveCustomer(@Argument CustomerDTO customerDTO) {
        Customer customerToSave = Customer.builder()
                .name(customerDTO.name())
                . email(customerDTO.email())
                .build();
        return customerRepository.save(customerToSave);
    }

    @GraphQlExceptionHandler
    public GraphQLError exceptionHandler(Exception e) {
        return GraphQLError.newError().message(e.getMessage()).build();
    }

}

record CustomerDTO(String name, String email) {}