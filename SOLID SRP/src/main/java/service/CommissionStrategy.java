package main.java.service;

import main.java.model.Account;
import main.java.service.commission.CommissionHandler;

public interface CommissionStrategy {
    CommissionHandler getTypeOfHandler(Account.Type type);
}
