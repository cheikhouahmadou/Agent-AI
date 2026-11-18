package sn.groupeisi.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sn.groupeisi.customerservice.domain.Customer;
import sn.groupeisi.customerservice.repository.CustomerRepository;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("hassan")
                    .email("hassan@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Imane")
                    .email("imane@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Brahim")
                    .email("brahim@gmail.com")
                    .build());
        };
    }

}
