package io.github.codejanovic.example.javalin.auth.user;

import io.github.codejanovic.example.javalin.auth.email.Email;
import io.github.codejanovic.example.javalin.auth.password.Password;
import io.github.codejanovic.example.javalin.misc.Identifiable;
import io.github.codejanovic.example.javalin.misc.Text;
import io.github.codejanovic.example.javalin.misc.UuidText;


public abstract class AbstractUser extends Identifiable.Abstract implements User {
    private final Password _password;
    private final Email _email;
    private final Text _salt;

    protected AbstractUser(final Password password,
                           final Email email) {
        super(email);
        _email = email;
        _password = password;
        _salt = new UuidText();
    }

    @Override
    public Text salt() {
        return null;
    }

    @Override
    public Password password() {
        return _password;
    }

    @Override
    public Email email() {
        return _email;
    }

}
