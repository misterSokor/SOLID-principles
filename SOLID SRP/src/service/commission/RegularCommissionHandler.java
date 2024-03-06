package service.commission;

import java.math.BigDecimal;

public class RegularCommissionHandler implements CommissionHandler {

    @Override
    public BigDecimal getCommission(BigDecimal amount) {
        return new BigDecimal("5");
    }
}

