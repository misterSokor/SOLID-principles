package service;

import model.Account;
import service.commission.CommissionHandler;

public interface CommissionStrategy {
    CommissionHandler get(Account.Type type);
}
