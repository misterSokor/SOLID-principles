package service.commission;

import java.math.BigDecimal;

public class UltraCommissionHandler implements CommissionHandler {
    public BigDecimal getCommission(BigDecimal amount) {
        return new BigDecimal("100");
    }
}
