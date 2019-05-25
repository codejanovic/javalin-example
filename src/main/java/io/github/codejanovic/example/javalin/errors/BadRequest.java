package io.github.codejanovic.example.javalin.errors;

public class BadRequest extends UsecaseError {
    public BadRequest(String message) {
        super(message);
    }
}
