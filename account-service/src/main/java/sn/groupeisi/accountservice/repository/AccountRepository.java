package sn.groupeisi.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.groupeisi.accountservice.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}
