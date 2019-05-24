package io.github.codejanovic.example.javalin.auth.user;

import io.github.codejanovic.example.javalin.auth.email.Email;
import io.github.codejanovic.example.javalin.auth.password.Password;
import io.github.codejanovic.example.javalin.misc.Identifiable;
import io.github.codejanovic.example.javalin.misc.Text;


public interface User extends Identifiable {
    Password password();
    Email email();

    Text salt();
}
