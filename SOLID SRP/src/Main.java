import dao.AccountDao;
import dao.AccountDaoCsvImpl;
import dao.AccountDaoImpl;
import model.Account;
import service.AccountService;
import service.AccountServiceImpl;
import service.BankService;
import service.BankServiceImpl;
import service.CommissionStrategy;
import service.CommissionStrategyImpl;
import service.commission.CommissionHandler;
import service.commission.GoldCommissionHandler;
import service.commission.RegularCommissionHandler;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //SOLID SINGLE RESPONSIBILITY PRINCIPLE SRP
        //SOLID OPEN/CLOSED PRINCIPLE OCP
        //SOLID LISKOV SUBSTITUTION PRINCIPLE LSP
        //SOLID INTERFACE SEGREGATION PRINCIPLE ISP
        //SOLID DEPENDENCY INVERSION PRINCIPLE DIP



        //Task: transfer money between two banking accounts
        AccountDao accountDao = new AccountDaoImpl();

        //setup commission strategy 1 way
//        Map<Account.Type, CommissionHandler> commissionHandlerMap = Map.of(
//                Account.Type.REGULAR, new GoldCommissionHandler(),
//                Account.Type.GOLD, new GoldCommissionHandler(),
//        );

        //setup commission strategy 2 way
        Map<Account.Type, CommissionHandler> commissionHandlerMap = new HashMap<>();
        commissionHandlerMap.put(Account.Type.REGULAR, new RegularCommissionHandler());
        commissionHandlerMap.put(Account.Type.GOLD, new GoldCommissionHandler());

        CommissionStrategy commissionStrategy = new CommissionStrategyImpl(commissionHandlerMap);



        AccountDaoCsvImpl accountDaoCsvImpl = new AccountDaoCsvImpl();

        BankService bankService = new BankServiceImpl(new AccountDaoCsvImpl(), commissionStrategy);
        bankService.transfer("777", "888", new BigDecimal("500"));
        System.out.println(accountDaoCsvImpl.getAccount("777"));
        System.out.println(accountDaoCsvImpl.getAccount("888"));


//        // Create two accounts
//        String accountNumber1 = "777";
//        String accountNumber2 = "888";
//
//        AccountService accountService = new AccountServiceImpl(accountDao);
//        accountService.createNewAccount(accountNumber1, Account.Type.REGULAR); // for use with commission
//        accountService.createNewAccount(accountNumber2, Account.Type.GOLD);
//
//        // Add money to account1 for testing the transfer
//        Account account1 = accountDao.getAccount("777");
//        account1.setAmount(new BigDecimal("1000"));
//        account1.setType(Account.Type.REGULAR);
//        accountDao.update(account1);
//
//        Account account2 = accountDao.getAccount("888");
//        account2.setAmount(new BigDecimal("0"));
//        account2.setType(Account.Type.GOLD);
//        accountDao.update(account2);
//
//        System.out.println("Before Transfer:");
//        System.out.println(accountDao.getAccount(accountNumber1));
//        System.out.println(accountDao.getAccount(accountNumber2));
//
//        // Perform a transfer
//        BankService bankService = new BankServiceImpl(accountDao, commissionStrategy);
//        bankService.transfer(accountNumber1, accountNumber2, new BigDecimal("500"));
//
//
//
//        System.out.println("After Transfer:");
//        System.out.println(accountDao.getAccount(accountNumber1));
//        System.out.println(accountDao.getAccount(accountNumber2));
    }
}
