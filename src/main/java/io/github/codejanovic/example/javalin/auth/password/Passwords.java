package io.github.codejanovic.example.javalin.auth.password;

public interface Passwords {
    String password();

    String passwordRepeated();

    default boolean match() {
        return password().equalsIgnoreCase(passwordRepeated());
    }

    default boolean notMatch() {
        return !match();
    }
}
