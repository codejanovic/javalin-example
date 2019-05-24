package io.github.codejanovic.example.javalin.auth;

import io.javalin.security.Role;

public enum Roles implements Role {
    Public,
    Chef,
    User
}
