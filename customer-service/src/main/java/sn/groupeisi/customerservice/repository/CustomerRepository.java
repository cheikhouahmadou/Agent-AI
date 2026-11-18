package sn.groupeisi.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.groupeisi.customerservice.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
