package vlpa.expman.model;

public enum PatternType {

    REGULAR(1, "Regular"),
    SKIP(2, "Skip"),
    AMOUNT(3, "Amount");

    private long id;
    private String displayName;

    PatternType(long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PatternType getPatternTypeById(long id) {
        for (PatternType pt : values()) {
            if (pt.getId() == id) {
                return pt;
            }
        }
        throw new RuntimeException("Pattern type with id=" + id + " doesn't exist!");
    }

    public static PatternType getPatternTypeByDisplayName(String name) {
        for (PatternType pt : values()) {
            if (pt.getDisplayName().equals(name)) {
                return pt;
            }
        }
        throw new RuntimeException("Pattern type with name=" + name + " doesn't exist!");
    }
}
