package io.github.codejanovic.example.javalin.service;


import io.github.codejanovic.example.javalin.auth.token.Token;
import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.misc.Text;

import java.util.Optional;

public interface AuthorizationService {
    Token decode(final Text bearer);
    Optional<User> authorize(final Text bearer) ;
    Optional<User> get(final Text userId) ;
    Text encode(final Token token);
}
