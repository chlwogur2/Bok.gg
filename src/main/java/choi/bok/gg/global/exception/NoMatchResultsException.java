package choi.bok.gg.global.exception;

public class NoMatchResultsException extends RuntimeException{

    public NoMatchResultsException() {
    }

    public NoMatchResultsException(String msg) {
        super(msg);
    }
}
