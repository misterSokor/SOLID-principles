package main.java.dao;

import main.java.model.Account;

public interface AccountDao {

    void createReport();


    Account getAccount(String accountNumber);

    void deleteAccount(Account account);

    void update(Account account);
}
