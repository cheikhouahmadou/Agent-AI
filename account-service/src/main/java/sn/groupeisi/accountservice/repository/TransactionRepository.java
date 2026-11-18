package sn.groupeisi.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.groupeisi.accountservice.domain.AccountTransaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<AccountTransaction, Long> {
    List<AccountTransaction> findByAccountId(Long accountId);
}
