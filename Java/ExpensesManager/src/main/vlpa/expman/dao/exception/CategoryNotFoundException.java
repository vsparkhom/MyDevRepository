package vlpa.expman.dao.exception;


public class CategoryNotFoundException extends RuntimeException {

    private static final String NOT_FOUND_MSG = "Category with following ID not found: ";

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(long categoryId) {
        super(NOT_FOUND_MSG + categoryId);
    }

}
