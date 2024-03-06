package service;

import dao.AccountDao;
import model.Account;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

//    @Override
//    public void createNewAccount(String accountNumber) {
//        Account account = new Account();
//        account.setAccountNumber(accountNumber);
//        accountDao.addAccount(account);
//    }

    @Override
    public void createNewAccount(String accountNumber, Account.Type type) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        accountDao.addAccount(account);
    }
}
