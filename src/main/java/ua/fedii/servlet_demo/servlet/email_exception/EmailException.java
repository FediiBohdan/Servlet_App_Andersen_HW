package ua.fedii.servlet_demo.servlet.email_exception;

public class EmailException extends RuntimeException {
    private final String message;

    public EmailException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
