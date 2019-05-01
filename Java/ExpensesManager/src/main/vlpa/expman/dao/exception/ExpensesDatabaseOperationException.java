package vlpa.expman.dao.exception;

public class ExpensesDatabaseOperationException extends RuntimeException {

    public ExpensesDatabaseOperationException(String message) {
        super(message);
    }

    public ExpensesDatabaseOperationException(Throwable cause) {
        super(cause);
    }

    public ExpensesDatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
