package main.java.service;

import main.java.model.Account;
import main.java.service.commission.CommissionHandler;

import java.util.Map;

public class CommissionStrategyImpl implements CommissionStrategy {
    private Map<Account.Type, CommissionHandler> commissionHandlersMap;

    public CommissionStrategyImpl(Map<Account.Type, CommissionHandler> commissionHandlersMap) {
        this.commissionHandlersMap = commissionHandlersMap;
    }

    @Override
    public CommissionHandler getTypeOfHandler(Account.Type type) {
        return commissionHandlersMap.get(type);
    }
}
