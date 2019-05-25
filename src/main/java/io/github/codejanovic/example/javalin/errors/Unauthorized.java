package io.github.codejanovic.example.javalin.errors;

public class Unauthorized extends UsecaseError {
    public Unauthorized(String message) {
        super(message);
    }
}
