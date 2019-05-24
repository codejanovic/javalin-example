package io.github.codejanovic.example.javalin.auth.password;

public class UserPasswords implements Passwords {
    private final String _password;
    private final String _passwordRepeated;

    public UserPasswords(final String password, final String passwordRepeated) {
        _password = password;
        _passwordRepeated = passwordRepeated;
    }

    @Override
    public String password() {
        return _password;
    }

    @Override
    public String passwordRepeated() {
        return _passwordRepeated;
    }
}
