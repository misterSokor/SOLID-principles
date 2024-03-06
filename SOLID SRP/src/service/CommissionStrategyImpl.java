package service;

import model.Account;
import service.commission.CommissionHandler;

import java.util.Map;

public class CommissionStrategyImpl implements CommissionStrategy {
    private Map<Account.Type, CommissionHandler> commissionHandlersMap;

    public CommissionStrategyImpl(Map<Account.Type, CommissionHandler> commissionHandlersMap) {
        this.commissionHandlersMap = commissionHandlersMap;
    }

    @Override
    public CommissionHandler get(Account.Type type) {
        return commissionHandlersMap.get(type);
    }
}
