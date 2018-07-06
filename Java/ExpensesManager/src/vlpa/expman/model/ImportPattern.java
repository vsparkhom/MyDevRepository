package vlpa.expman.model;

public class ImportPattern {

    private String text;
    private Category category;

    public ImportPattern() {
    }

    public ImportPattern(String text, Category category) {
        this.text = text;
        this.category = category;
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

    @Override
    public String toString() {
        return "ImportPattern{" +
                "text=" + text +
                ", category=" + category +
                '}';
    }
}
