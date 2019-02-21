package vlpa.expman.model;

public class ImportPattern {

    private long id;
    private String text;
    private Category category;
    private PatternType type;
    private PatternPriority priority;
    private double amount;

    public ImportPattern() {
    }

    public ImportPattern(long id, String text, Category category, PatternType type) {
        this(id, text, category, type, PatternPriority.MEDIUM, 0);
    }

    public ImportPattern(long id, String text, Category category, PatternType type, PatternPriority priority, double amount) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.type = type;
        this.priority = priority;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PatternType getType() {
        return type;
    }

    public void setType(PatternType type) {
        this.type = type;
    }

    public PatternPriority getPriority() {
        return priority;
    }

    public void setPriority(PatternPriority priority) {
        this.priority = priority;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ImportPattern{" +
                "id=" + id +
                ", text=" + text +
                ", category=" + category +
                ", type=" + type +
                ", priority=" + priority +
                ", amount=" + amount +
                '}';
    }
}
