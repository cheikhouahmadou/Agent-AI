package sn.groupeisi.accountservice.exceptions;

public class AccountNotFoundExecption extends RuntimeException {
    public AccountNotFoundExecption(String message) {
        super(message);
    }
}
