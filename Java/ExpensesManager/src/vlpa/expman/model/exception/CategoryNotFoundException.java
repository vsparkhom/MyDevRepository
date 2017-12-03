package vlpa.expman.model.exception;


public class CategoryNotFoundException extends RuntimeException {

    private static final String NOT_FOUND_MSG = "Category with following ID not found: ";

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(int categoryId) {
        super(NOT_FOUND_MSG + categoryId);
    }

}
