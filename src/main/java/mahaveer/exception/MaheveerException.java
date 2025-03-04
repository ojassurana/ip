package mahaveer.exception;

/**
 * The MaheveerException class represents custom exceptions that occur
 * within the Mahaveer application. It extends the standard Exception class,
 * allowing for error messages specific to the application's domain.
 */
public class MaheveerException extends Exception {

    /**
     * Constructs a new MaheveerException with the specified detail message.
     *
     * @param message the detail message explaining the exception
     */
    public MaheveerException(String message) {
        super(message);
    }
}
