package dao;

import db.Storage;
import model.Account;

public class AccountDaoImpl implements AccountDao {

    @Override
    public void addAccount(Account account) {
        Storage.accounts.add(account);
    }

    @Override
    public Account getAccount(String accountNumber) {
        return Storage.accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst().get();
    }

    public void deleteAccount(Account account) {
        Storage.accounts.remove(account);
    }

    @Override
    public void update(Account account) {
        // 1. find
        // 2. remove
        // 3. add

        Account accountFromDb =  getAccount(account.getAccountNumber());
        deleteAccount(accountFromDb);
        addAccount(account);
    }
}
