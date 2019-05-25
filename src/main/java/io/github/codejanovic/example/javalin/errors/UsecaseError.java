package io.github.codejanovic.example.javalin.errors;

public abstract class UsecaseError extends RuntimeException {
    public UsecaseError(String message) {
        super(message);
    }

    public UsecaseError(String message, Throwable cause) {
        super(message, cause);
    }

}
