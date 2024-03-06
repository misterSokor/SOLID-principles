package service.commission;

import java.math.BigDecimal;

public class PlatinumCommissionHandler implements CommissionHandler {
    public BigDecimal getCommission(BigDecimal amount) {
        return new BigDecimal("0");
    }
}
