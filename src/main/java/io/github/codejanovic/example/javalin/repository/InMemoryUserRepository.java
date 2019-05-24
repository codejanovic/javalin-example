package io.github.codejanovic.example.javalin.repository;

import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.misc.Text;
import org.jusecase.inject.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryUserRepository implements UserRepository {
    private final Map<Text, User> _map = new HashMap<>();

    @Override
    public Optional<User> findById(final Text identifier) {
        return Optional.ofNullable(_map.get(identifier));
    }

    @Override
    public Optional<User> findByEmail(final Text email) {
        return findById(email);
    }

    @Override
    public User persistOrThrow(final User user) {
        _map.put(user.email(), user);
        return user;
    }
}
