package io.github.codejanovic.example.javalin.service;


import io.github.codejanovic.example.javalin.auth.email.Email;
import io.github.codejanovic.example.javalin.misc.Text;

public interface LoginService {
    Text login(Email email, Text password);
}
