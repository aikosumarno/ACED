package model.exception;

/**
 * Represents the exception that can occur when
 * printing the event log.
 */

public class LogException extends Exception {

    // EFFECTS: initializes exception
    public LogException() {
        super("Error printing log");
    }

    // EFFECTS: initializes exception with given message
    public LogException(String msg) {
        super(msg);
    }
}
