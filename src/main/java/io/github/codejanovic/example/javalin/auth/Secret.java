package io.github.codejanovic.example.javalin.auth;


import io.github.codejanovic.example.javalin.misc.Text;

import java.util.UUID;

public class Secret extends Text.CaseSensitive {

    public Secret() {
        super(UUID.randomUUID().toString());
    }
}
