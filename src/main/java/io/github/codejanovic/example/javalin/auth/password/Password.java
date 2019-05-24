package io.github.codejanovic.example.javalin.auth.password;


import io.github.codejanovic.example.javalin.misc.Text;

public interface Password {
    Text hash();

    Text salt();
}
