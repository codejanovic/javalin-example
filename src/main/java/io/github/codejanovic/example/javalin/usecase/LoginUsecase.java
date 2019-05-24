package io.github.codejanovic.example.javalin.usecase;


import io.github.codejanovic.example.javalin.auth.email.Email;
import io.github.codejanovic.example.javalin.misc.Text;
import io.github.codejanovic.example.javalin.service.LoginService;
import org.jusecase.Usecase;
import org.jusecase.inject.Component;

import javax.inject.Inject;

@Component
public class LoginUsecase implements Usecase<LoginUsecase.LoginRequest, LoginUsecase.LoginResponse> {

    public static class LoginRequest {
        public String email;
        public String password;

        public LoginRequest withEmail(final String email) {
            this.email = email;
            return this;
        }

        public LoginRequest withPassword(final String password) {
            this.password = password;
            return this;
        }
    }

    @Inject
    private LoginService _loginService;

    @Override
    public LoginResponse execute(final LoginRequest request) {
        final Email email = new Email.Unregistered(request.email);
        return new LoginResponse().withToken(_loginService.login(email, new Text.CaseSensitive(request.password)).asString());
    }

    public class LoginResponse {
        /**
         * JWT Token that needs to be attached to every following request via Authentication Header:
         * Authentication Bearer {TOKEN}
         */
        public String token;

        public LoginResponse withToken(final String token) {
            this.token = token;
            return this;
        }
    }

}
