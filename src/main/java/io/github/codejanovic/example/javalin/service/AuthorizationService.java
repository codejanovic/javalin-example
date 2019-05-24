package io.github.codejanovic.example.javalin.service;


import io.github.codejanovic.example.javalin.auth.token.Token;
import io.github.codejanovic.example.javalin.misc.Text;

public interface AuthorizationService {
    Token decode(final Text bearer);

    Text encode(final Token token);
}
