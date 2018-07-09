package vlpa.expman.model;

public class ImportPattern {

    private long id;
    private String text;
    private Category category;

    public ImportPattern() {
    }

    public ImportPattern(long id, String text, Category category) {
        this.id = id;
        this.text = text;
        this.category = category;
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

    @Override
    public String toString() {
        return "ImportPattern{" +
                "id=" + id +
                ", text=" + text +
                ", category=" + category +
                '}';
    }
}
