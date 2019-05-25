package io.github.codejanovic.example.javalin.errors;

public class InternalServerError extends UsecaseError {
    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(String message, Throwable cause) {
        super(message, cause);
    }

}
