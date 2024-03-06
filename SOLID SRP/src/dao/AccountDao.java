package dao;

import model.Account;

public interface AccountDao {

    void addAccount(Account account);

    Account getAccount(String accountNumber);

    void deleteAccount(Account account);

    void update(Account account);
}
