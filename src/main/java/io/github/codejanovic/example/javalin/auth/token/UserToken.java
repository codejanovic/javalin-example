package io.github.codejanovic.example.javalin.auth.token;


import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.misc.Text;


public class UserToken implements Token {
    private final Text _userId;

    public UserToken(User user) {
        _userId = user.email();
    }

    @Override
    public Text userId() {
        return _userId;
    }
}
