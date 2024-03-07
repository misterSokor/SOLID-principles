package main.java.service;

import java.math.BigDecimal;

public interface BankService {
    void transfer(String fromAccount, String toAccount, BigDecimal valueOf);
}
