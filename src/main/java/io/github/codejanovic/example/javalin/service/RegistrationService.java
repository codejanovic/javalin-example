package io.github.codejanovic.example.javalin.service;


import io.github.codejanovic.example.javalin.auth.password.Passwords;
import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.misc.Text;

public interface RegistrationService {

    Text register(final User user, final Passwords passwords);
}
