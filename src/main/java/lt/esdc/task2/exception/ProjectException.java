package lt.esdc.task2.exception;

public class ProjectException extends Exception {
    public ProjectException() {
        super();
    }

    public ProjectException(String message) {
        super(message);
    }

    public ProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectException(Throwable cause) {
        super(cause);
    }
}
