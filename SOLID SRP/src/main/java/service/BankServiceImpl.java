package main.java.service;

import main.java.dao.AccountDao;
import main.java.model.Account;

import java.math.BigDecimal;

public class BankServiceImpl implements BankService {
    private AccountDao accountDao;
    private CommissionStrategy commissionStrategy;

    public BankServiceImpl(AccountDao accountDao, CommissionStrategy commissionStrategy) {
        this.accountDao = accountDao;
        this.commissionStrategy = commissionStrategy;
    }

    @Override
    public void transfer(String fromAccountNumber,
                         String toAccountNumber,
                         BigDecimal amount) {
        //getTypeOfHandler accounts from storage
        Account fromAccount = accountDao.getAccount(fromAccountNumber);
        Account toAccount = accountDao.getAccount(toAccountNumber);

        BigDecimal commission = commissionStrategy.getTypeOfHandler(fromAccount.getType()).getCommission(amount);

        BigDecimal newValueFrom = fromAccount.getAmount().subtract(amount).subtract(commission);
        fromAccount.setAmount(newValueFrom);

        BigDecimal newValueTo = toAccount.getAmount().add(amount);
        toAccount.setAmount(newValueTo);

        accountDao.update(fromAccount);
        accountDao.update(toAccount);
    }
}
