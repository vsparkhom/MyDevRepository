package vlpa.expman.model;

public class ImportPattern {

    private long id;
    private String text;
    private Category category;
    private PatternType type;
    private PatternPriority priority;
    private double amount;

//    private ImportPattern() {
//    }
//
//    private ImportPattern(long id, String text, Category category, PatternType type) {
//        this(id, text, category, type, PatternPriority.MEDIUM, 0);
//    }
//
//    private ImportPattern(long id, String text, Category category, PatternType type, PatternPriority priority, double amount) {
//        this.id = id;
//        this.text = text;
//        this.category = category;
//        this.type = type;
//        this.priority = priority;
//        this.amount = amount;
//    }

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

    public static Builder builder() {
        return new ImportPattern().new Builder();
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

    public class Builder {

        private Builder() {
        }

        public ImportPattern build() {
            return ImportPattern.this;
        }

        public Builder setId(long id) {
            ImportPattern.this.id = id;
            return this;
        }

        public Builder setText(String text) {
            ImportPattern.this.text = text;
            return this;
        }

        public Builder setCategory(Category category) {
            ImportPattern.this.category = category;
            return this;
        }

        public Builder setType(PatternType type) {
            ImportPattern.this.type = type;
            return this;
        }

        public Builder setPriority(PatternPriority priority) {
            ImportPattern.this.priority = priority;
            return this;
        }

        public Builder setAmount(double amount) {
            ImportPattern.this.amount = amount;
            return this;
        }
    }
}
