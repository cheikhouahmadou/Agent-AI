package sn.groupeisi.accountservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sn.groupeisi.accountservice.domain.AccountType;
import sn.groupeisi.accountservice.repository.AccountRepository;
import sn.groupeisi.accountservice.service.EBankService;

@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EBankService  eBankService) {
        return args -> {
            eBankService.saveNewAccount(65000, AccountType.CURRENT_ACCOUNT, "MAD", Long.valueOf(1));
            eBankService.saveNewAccount(12000, AccountType.SAVING_ACCOUNT, "MAD", Long.valueOf(1));
            eBankService.saveNewAccount(3000, AccountType.CURRENT_ACCOUNT, "MAD", Long.valueOf(2));


            eBankService.getAllAccounts().forEach(acc->{
                for (int i = 0; i < 5; i++) {
                    eBankService.credit(acc.getId(), 500+Math.random()*230000);
                    eBankService.debit(acc.getId(), 500+Math.random()*10000);
                }
            });
        };
    }

}
