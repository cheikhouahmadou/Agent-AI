package sn.groupeisi.accountservice.mcp;

import org.springframework.ai.mcp.annotation.McpArg;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sn.groupeisi.accountservice.domain.Account;
import sn.groupeisi.accountservice.domain.AccountTransaction;
import sn.groupeisi.accountservice.domain.AccountType;
import sn.groupeisi.accountservice.service.EBankService;

import java.util.List;

@Component
public class EBankMCPController {

    private EBankService eBankService;

    public EBankMCPController(EBankService eBankService) {
        this.eBankService = eBankService;
    }

    @McpTool(description = "Get account by Id ")
    public Account getAccountById(@McpArg(description = "the account id") String accountId) {
        return eBankService.getAccountById(accountId);
    }
    @McpTool(description = "Get all accounts ")
    public List<Account> getAllAccounts() {
        return eBankService.getAllAccounts();
    }

    @McpTool(description = "save a new account")
    public Account saveNewAccount(@McpArg(description = "the initial balance") double initialBalance,
                                  @McpArg(description = "the account Type") AccountType type,
                                  @McpArg(description = "the account currency") String currency,
                                  @McpArg(description = "the customer Id") Long customerId) {
        return eBankService.saveNewAccount(initialBalance, type, currency, customerId);
    }

    @McpTool(description = "credit account ")
    public AccountTransaction credit(@McpArg(description = "the account Id") String accountId,
                                     @McpArg(description = "the credited amount") double amount) {
        return eBankService.credit(accountId, amount);
    }

    @McpTool(description = "debit account ")
    public AccountTransaction debit(@McpArg(description = "the account id") String accountId,
                                    @McpArg(description = "the debited amount") double amount) {
        return eBankService.debit(accountId, amount);
    }


}
