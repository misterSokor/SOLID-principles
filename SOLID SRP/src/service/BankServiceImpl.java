package service;

import dao.AccountDao;
import model.Account;

import java.math.BigDecimal;

public class BankServiceImpl implements BankService {
    private AccountDao accountDao;
    private CommissionStrategy commissionStrategy;

    public BankServiceImpl(AccountDao accountDao, CommissionStrategy commissionStrategy) {
        this.accountDao = accountDao;
        this.commissionStrategy = commissionStrategy;
    }

    public BankServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String fromAccountNumber,
                         String toAccountNumber,
                         BigDecimal amount) {
        //get accounts from storage
        Account fromAccount = accountDao.getAccount(fromAccountNumber);
        Account toAccount = accountDao.getAccount(toAccountNumber);

        //TODO: check if fromAccount and toAccount are not null
        // if Account.Type.REGULAR --> commission is 1% from amount
        // if Account.Type.GOLD --> commission is 3 UAH
        // if Account.Type.PLATINUM --> commission is 0 UAH
        // if Account.Type.USUAL --> commission is 10 UAH from amount

//        BigDecimal commission = new BigDecimal(0);
//        if (fromAccount.getType() == Account.Type.REGULAR) {
//            commission = amount.multiply(new BigDecimal("0.01"));
//        } else if (fromAccount.getType() == Account.Type.GOLD) {
//            commission = new BigDecimal("3");
//        } else if (fromAccount.getType() == Account.Type.USUAL) {
//            commission = new BigDecimal("10");
//        }
         // the same as above but using CommissionStrategy
        BigDecimal commission = commissionStrategy.get(fromAccount.getType()).getCommission(amount);


        //transfer money between accounts and update amounts value
        BigDecimal newValueFrom = fromAccount.getAmount().subtract(amount).subtract(commission);
        fromAccount.setAmount(newValueFrom);

        BigDecimal newValueTo = toAccount.getAmount().add(amount);
        toAccount.setAmount(newValueTo);

        accountDao.update(fromAccount);
        accountDao.update(toAccount);
    }
}
