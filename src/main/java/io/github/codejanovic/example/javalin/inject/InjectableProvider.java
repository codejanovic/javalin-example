package io.github.codejanovic.example.javalin.inject;

import io.github.codejanovic.example.javalin.Assembler;

import javax.inject.Provider;

public class InjectableProvider implements Provider<Injectable> {

    @Override
    public Injectable get() {
        return Assembler.instance;
    }
}
