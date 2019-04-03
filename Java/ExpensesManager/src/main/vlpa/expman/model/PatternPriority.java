package vlpa.expman.model;

public enum PatternPriority {

    LOW(0),
    MEDIUM(10),
    HIGH(100),
    CRITICAL(1000);

    private long id;

    PatternPriority(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static PatternPriority getPatternPriorityById(long id) {
        for (PatternPriority pp : values()) {
            if (pp.getId() == id) {
                return pp;
            }
        }
        throw new RuntimeException("Pattern priority with id=" + id + " doesn't exist!");
    }

    public static PatternPriority getPatternPriorityByName(String name) {
        for (PatternPriority pp : values()) {
            if (pp.name().equals(name)) {
                return pp;
            }
        }
        throw new RuntimeException("Pattern priority with id=" + name + " doesn't exist!");
    }
}
