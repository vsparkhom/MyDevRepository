public abstract class PaidService {

    private String id;
    private String name;
    private double costs;

    //базовый конструктор
    protected PaidService(String id, String name, double costs) {
        this.id = id;
        this.name = name;
        this.costs = costs;
    }

    //абстрактный метод - должен быть пустым и переопределятся в классах-наследниках
    public abstract double calculateAverageMonthlyCosts();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    @Override
    public String toString() {
        return "PaidService[id: " + id + ", name: " + name + ", costs: " + costs + "]";
    }
}
