package main.java;

import main.java.dao.AccountDao;
import main.java.dao.AccountDaoCsvImpl;
import main.java.model.Account;
import main.java.service.BankService;
import main.java.service.BankServiceImpl;
import main.java.service.CommissionStrategy;
import main.java.service.CommissionStrategyImpl;
import main.java.service.commission.CommissionHandler;
import main.java.service.commission.GoldCommissionHandler;
import main.java.service.commission.RegularCommissionHandler;


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

        AccountDao accountDao = new AccountDaoCsvImpl();

        Map<Account.Type, CommissionHandler> commissionHandlerMap = new HashMap<>();
        commissionHandlerMap.put(Account.Type.REGULAR, new RegularCommissionHandler());
        commissionHandlerMap.put(Account.Type.GOLD, new GoldCommissionHandler());

        CommissionStrategy commissionStrategy = new CommissionStrategyImpl(commissionHandlerMap);

        AccountDao accountDaoCsvImpl = new AccountDaoCsvImpl();

        BankService bankService = new BankServiceImpl(new AccountDaoCsvImpl(), commissionStrategy);
        bankService.transfer("777", "888", new BigDecimal("500"));
        accountDaoCsvImpl.createReport();
        System.out.println(accountDaoCsvImpl.getAccount("777"));
        System.out.println(accountDaoCsvImpl.getAccount("888"));
    }
}
