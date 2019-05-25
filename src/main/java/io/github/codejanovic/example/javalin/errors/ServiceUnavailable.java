package io.github.codejanovic.example.javalin.errors;

public class ServiceUnavailable extends UsecaseError {
    public ServiceUnavailable(String message) {
        super(message);
    }

    public ServiceUnavailable(String message, Throwable cause) {
        super(message, cause);
    }

}
