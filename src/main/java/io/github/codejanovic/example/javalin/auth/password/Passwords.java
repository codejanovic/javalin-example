package io.github.codejanovic.example.javalin.auth.password;

public interface Passwords {
    String password();

    String passwordRepeated();

    default boolean match() {
        if (password() == null || passwordRepeated() == null || password().isEmpty()) {
            return false;
        }
        return password().equalsIgnoreCase(passwordRepeated());
    }

    default boolean notMatch() {
        return !match();
    }
}
