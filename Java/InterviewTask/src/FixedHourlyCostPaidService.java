import java.math.BigDecimal;

public class FixedHourlyCostPaidService extends PaidService {

    public FixedHourlyCostPaidService(String id, String name, double costs) {
        super(id, name, costs);
    }

    @Override
    public double calculateAverageMonthlyCosts() {
        return BigDecimal.valueOf(30.0).multiply(BigDecimal.valueOf(24.00))
                .multiply(BigDecimal.valueOf(getCosts())).doubleValue(); //actually this is a 30 * 24 * costs
    }

    @Override
    public String toString() {
        return super.toString() + "[avMonthlyCosts: " + calculateAverageMonthlyCosts() + "]";
    }

}
