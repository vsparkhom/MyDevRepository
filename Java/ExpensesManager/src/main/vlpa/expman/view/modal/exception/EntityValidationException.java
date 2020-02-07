package vlpa.expman.view.modal.exception;

public class EntityValidationException extends RuntimeException {

    private String entityName;

    public EntityValidationException(String entityName, String message) {
        super(message);
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
