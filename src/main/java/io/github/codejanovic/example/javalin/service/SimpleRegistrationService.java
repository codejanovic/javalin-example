package io.github.codejanovic.example.javalin.service;

import io.github.codejanovic.example.javalin.auth.password.Passwords;
import io.github.codejanovic.example.javalin.auth.token.UserToken;
import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.misc.Text;
import io.github.codejanovic.example.javalin.repository.UserRepository;
import io.javalin.BadRequestResponse;
import org.jusecase.inject.Component;

import javax.inject.Inject;

@Component
public class SimpleRegistrationService implements RegistrationService {

    @Inject
    UserRepository _userRepository;
    @Inject
    AuthorizationService _authorizationService;

    @Override
    public Text register(final User user, final Passwords passwords) {
        validateOrThrow(user, passwords);

        _userRepository.persistOrThrow(user);
        return _authorizationService.encode(new UserToken(user));
    }

    private void validateOrThrow(final User user, final Passwords passwords) {
        if (passwords.notMatch()) {
            throw new BadRequestResponse("passwords dont match!");
        }
    }

}
