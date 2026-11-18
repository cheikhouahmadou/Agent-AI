package sn.groupeisi.accountservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.groupeisi.accountservice.domain.*;
import sn.groupeisi.accountservice.exceptions.AccountNotFoundExecption;
import sn.groupeisi.accountservice.exceptions.BalanceNotSuffiecientException;
import sn.groupeisi.accountservice.exceptions.NegativeAmountException;
import sn.groupeisi.accountservice.repository.AccountRepository;
import sn.groupeisi.accountservice.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EBankService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public EBankService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account getAccountById(String accountId) {
        Account account =  accountRepository.findById(accountId).orElseThrow(
                ()->new AccountNotFoundExecption("Account not found "));
        return account;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account saveNewAccount(double initialBalance, AccountType type, String currency, Long customerId){

        if (initialBalance < 0) throw new NegativeAmountException("initial balance cannot be negative");

        Account account = Account.builder()
                .id(UUID.randomUUID().toString())
                .currency(currency)
                .type(type)
                .status(AccountStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .customerId(customerId)
                .balance(initialBalance)
                .build();
        Account savedAccount = accountRepository.save(account);
        AccountTransaction transaction = AccountTransaction.builder()
                .amount(initialBalance)
                .type(TransactionType.CREDIT)
                .localDateTime(LocalDateTime.now())
                .account(savedAccount)
                .build();
        transactionRepository.save(transaction);
        return savedAccount;
    }

    @Transactional
    public AccountTransaction credit(String accountId, double amount){
        if (amount <= 0) throw new NegativeAmountException("amount cannot be negative");
        Account account = getAccountById(accountId);
        AccountTransaction accountTransaction =
                AccountTransaction.builder()
                        .amount(amount)
                        .localDateTime(LocalDateTime.now())
                        .type(TransactionType.CREDIT)
                        .account(account)
                        .build();
        account.setBalance(account.getBalance()+amount);
        Account savedAccount = accountRepository.save(account);
        AccountTransaction transaction =  transactionRepository.save(accountTransaction);
        return transaction ;
    }

    @Transactional
    public AccountTransaction debit(String accountId, double amount){
        if (amount <= 0) throw new NegativeAmountException("amount cannot be negative");
        Account account = getAccountById(accountId);
        if (account.getBalance() < amount) throw new BalanceNotSuffiecientException("amount cannot be negative");
        AccountTransaction accountTransaction =
                AccountTransaction.builder()
                        .amount(amount)
                        .localDateTime(LocalDateTime.now())
                        .type(TransactionType.DEBIT)
                        .account(account)
                        .build();
        AccountTransaction transaction =  transactionRepository.save(accountTransaction);
        account.setBalance(account.getBalance()-amount);
        Account savedAccount = accountRepository.save(account);
        return transaction ;
    }
}
