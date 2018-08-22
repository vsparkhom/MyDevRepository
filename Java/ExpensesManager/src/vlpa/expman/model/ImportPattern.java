package vlpa.expman.model;

import vlpa.expman.view.modal.pattern.PatternType;

public class ImportPattern {

    private long id;
    private String text;
    private Category category;
    private PatternType type;

    public ImportPattern() {
    }

    public ImportPattern(long id, String text, Category category, PatternType type) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.type = type;
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

    @Override
    public String toString() {
        return "ImportPattern{" +
                "id=" + id +
                ", text=" + text +
                ", category=" + category +
                ", type=" + type +
                '}';
    }
}
