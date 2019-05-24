package io.github.codejanovic.example.javalin.service;


import io.github.codejanovic.example.javalin.auth.email.Email;
import io.github.codejanovic.example.javalin.auth.password.PBKDF2Password;
import io.github.codejanovic.example.javalin.auth.password.Password;
import io.github.codejanovic.example.javalin.auth.token.UserToken;
import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.misc.Text;
import io.github.codejanovic.example.javalin.repository.UserRepository;
import io.javalin.UnauthorizedResponse;
import org.jusecase.inject.Component;

import javax.inject.Inject;
import java.util.Optional;

@Component
public class JwtLoginService implements LoginService {

    @Inject
    private AuthorizationService _authorizationService;
    @Inject
    private UserRepository _userRepository;

    @Override
    public Text login(Email email, Text passwordAsString) {
        final Optional<User> registeredUser = _userRepository.findByEmail(email);
        if (!registeredUser.isPresent()) {
            throw new UnauthorizedResponse("Login failed");
        }
        final Password hashedPassword = new PBKDF2Password(passwordAsString, registeredUser.get().salt());
        if (!hashedPassword.hash().equals(registeredUser.get().password().hash())) {
            throw new UnauthorizedResponse("Login failed");
        }

        return _authorizationService.encode(new UserToken(registeredUser.get()));
    }
}
