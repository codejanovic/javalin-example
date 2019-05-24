package io.github.codejanovic.example.javalin.auth.user;

import io.github.codejanovic.example.javalin.auth.email.Email;
import io.github.codejanovic.example.javalin.misc.Identifiable;
import org.eclipse.jetty.util.security.Password;

public interface User extends Identifiable {
    Password password();

    Email email();
}
