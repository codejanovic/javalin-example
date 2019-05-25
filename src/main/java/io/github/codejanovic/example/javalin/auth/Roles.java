package io.github.codejanovic.example.javalin.auth;

import io.javalin.security.Role;

public enum Roles implements Role {
    Public,
    Chef,
    User;

    public boolean isPublic() {
        return this == Public;
    }

    public boolean isChef() {
        return this == Chef;
    }

    public boolean isUser() {
        return this == User;
    }

    public boolean isAuthenticated() {
        return isUser() || isChef();
    }
}
