package fa.appcode.web.commons.exception;

public class DateRangeException extends RuntimeException{
    String message;

    public DateRangeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
