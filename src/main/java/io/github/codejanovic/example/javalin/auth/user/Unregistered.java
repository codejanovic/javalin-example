package io.github.codejanovic.example.javalin.auth.user;

import io.github.codejanovic.example.javalin.auth.email.Email;
import io.github.codejanovic.example.javalin.auth.password.PBKDF2Password;
import io.github.codejanovic.example.javalin.misc.Text;

public class Unregistered extends AbstractUser {

    public Unregistered(final String password, final String email) {
        super(new PBKDF2Password(new Text.CaseSensitive(password)),
                new Email.Unregistered(email));
    }
}
