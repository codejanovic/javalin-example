package io.github.codejanovic.example.javalin.auth.token;


import io.github.codejanovic.example.javalin.misc.Text;

public interface Token {
    Text userId();

    default boolean isValid() {
        return !userId().asString().isEmpty();
    }
}
