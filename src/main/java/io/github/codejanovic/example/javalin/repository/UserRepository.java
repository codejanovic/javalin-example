package io.github.codejanovic.example.javalin.repository;

import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.misc.Text;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(final Text identifier);

    Optional<User> findByEmail(final Text email);

    User persistOrThrow(final User user);
}