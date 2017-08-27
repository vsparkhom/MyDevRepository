package ua.storchaka.pract5.exceptions;

public class CoderException extends Exception
{
    /**
     * Empty constructor
     */
    public CoderException() { }
    
    /**
     * Constructor creates exception with specified message
     * 
     * @param message message
     */
    public CoderException(String message) {
        super(message);
    }
   
    /**
     * Constructor creates exception with specified message and cause
     * 
     * @param message message
     * @param cause exception cause
     */   
    public CoderException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor creates exception with specified cause
     * 
     * @param cause exception cause
     */
    public CoderException(Throwable cause) {
        super(cause);
    }
}