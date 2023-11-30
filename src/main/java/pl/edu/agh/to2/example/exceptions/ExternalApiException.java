package pl.edu.agh.to2.example.exceptions;

public class ExternalApiException extends RuntimeException {
    public ExternalApiException(Throwable cause) {
        super("Api communication error", cause);
    }
}
