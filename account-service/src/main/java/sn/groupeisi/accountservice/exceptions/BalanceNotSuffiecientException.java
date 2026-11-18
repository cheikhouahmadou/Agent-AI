package sn.groupeisi.accountservice.exceptions;

public class BalanceNotSuffiecientException extends RuntimeException {
    public BalanceNotSuffiecientException(String message) {
        super(message);
    }
}
