package main.java.service.commission;

import java.math.BigDecimal;

public class GoldCommissionHandler implements CommissionHandler {
    public BigDecimal getCommission (BigDecimal amount) {

        return new BigDecimal("3");
    }
}
