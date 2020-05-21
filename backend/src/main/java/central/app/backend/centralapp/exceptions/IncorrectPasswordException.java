package central.app.backend.centralapp.exceptions;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(String message) {super(message);}
    public IncorrectPasswordException() { this("Incorrect Password Exception"); }
}
