package vlpa.expman.model;

public class Category {

    private int id;
    private String name;
    private double limit;

    public Category(int id, String name, double limit) {
        this.id = id;
        this.name = name;
        this.limit = limit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", limit=" + limit +
                '}';
    }

}
