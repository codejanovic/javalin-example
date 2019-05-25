package io.github.codejanovic.example.javalin.auth.user;

import io.github.codejanovic.example.javalin.auth.email.Email;
import io.github.codejanovic.example.javalin.auth.password.Password;
import io.github.codejanovic.example.javalin.misc.Identifiable;


public abstract class AbstractUser extends Identifiable.Abstract implements User {
    protected final Password _password;

    @Override
    public MutableUser mutable() {
        return new MutableUser(_email.asString(), email().asString());
    }

    protected final Email _email;

    protected AbstractUser(final Password password,
                           final Email email) {
        super(email);
        _email = email;
        _password = password;
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
