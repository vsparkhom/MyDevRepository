package vlpa.expman.view.modal.exception;

public class ImportExpensesException extends RuntimeException {

    public ImportExpensesException() {
    }

    public ImportExpensesException(String message) {
        super(message);
    }

    public ImportExpensesException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImportExpensesException(Throwable cause) {
        super(cause);
    }
}
