package service;

import model.Account;

public interface AccountService {
//    void createNewAccount(String accountNumber); // for use without commission
void createNewAccount(String accountNumber, Account.Type type); // for use with commission
}
