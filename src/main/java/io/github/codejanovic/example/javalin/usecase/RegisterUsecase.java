package io.github.codejanovic.example.javalin.usecase;


import io.github.codejanovic.example.javalin.auth.password.UserPasswords;
import io.github.codejanovic.example.javalin.auth.user.Unregistered;
import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.service.RegistrationService;
import org.jusecase.Usecase;
import org.jusecase.inject.Component;

import javax.inject.Inject;

@Component
public class RegisterUsecase implements Usecase<RegisterUsecase.RegisterRequest, RegisterUsecase.RegisterResponse> {

    public static class RegisterRequest {
        public String email;
        public String password;
        public String passwordRepeated;


        public RegisterRequest withEmail(final String email) {
            this.email = email;
            return this;
        }

        public RegisterRequest withPassword(final String password) {
            this.password = password;
            return this;
        }

        public RegisterRequest withPasswordRepeated(final String passwordRepeated) {
            this.passwordRepeated = passwordRepeated;
            return this;
        }
    }

    public static class RegisterResponse {
        /**
         * JWT Token that needs to be attached to every following request via Authentication Header:
         * Authentication Bearer {TOKEN}
         */
        public String token;

        public RegisterResponse withToken(final String token) {
            this.token = token;
            return this;
        }
    }

    @Inject
    RegistrationService _registrationService;

    @Override
    public RegisterResponse execute(final RegisterRequest request) {
        final User unregisteredUser = new Unregistered(
                request.password,
                request.email);

        return new RegisterResponse().withToken(_registrationService.register(unregisteredUser, new UserPasswords(request.password, request.passwordRepeated)).asString());
    }

}
