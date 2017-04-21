public class FixedMonthlyCostPaidService extends PaidService {

    public FixedMonthlyCostPaidService(String id, String name, double costs) {
        super(id, name, costs);
    }

    @Override
    public double calculateAverageMonthlyCosts() {
        return getCosts();
    }

    @Override
    public String toString() {
        return super.toString() + "[avMonthlyCosts: " + calculateAverageMonthlyCosts() + "]";
    }
}
