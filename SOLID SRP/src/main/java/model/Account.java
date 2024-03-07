package main.java.model;

import java.math.BigDecimal;

public class Account {
    private String accountNumber;
    private BigDecimal amount = new BigDecimal(0);
    private Type type;

    public Account() {
    }

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }

    public enum Type {
        REGULAR, GOLD, PLATINUM, USUAL;
    }
}
