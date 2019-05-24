package io.github.codejanovic.example.javalin;

import io.github.codejanovic.example.javalin.auth.Secret;
import io.github.codejanovic.example.javalin.chrono.DateProvider;
import io.github.codejanovic.example.javalin.inject.InjectUsecaseExecutor;
import io.github.codejanovic.example.javalin.inject.Injectable;
import io.github.codejanovic.example.javalin.inject.InjectableProvider;
import io.github.codejanovic.example.javalin.repository.InMemoryUserRepository;
import io.github.codejanovic.example.javalin.service.JwtAuthorizationService;
import io.github.codejanovic.example.javalin.service.JwtLoginService;
import io.github.codejanovic.example.javalin.service.SimpleRegistrationService;
import io.github.codejanovic.example.javalin.usecase.LoginUsecase;
import io.github.codejanovic.example.javalin.usecase.RegisterUsecase;

public class Assembler extends InjectUsecaseExecutor implements Injectable {

    public static final Assembler instance = new Assembler();



    public Assembler() {
        injector.addProvider(InjectableProvider.class);
        injector.add(Secret.class);
        injector.add(new DateProvider.Default());

        injector.add(InMemoryUserRepository.class);

        injector.add(JwtAuthorizationService.class);
        injector.add(JwtLoginService.class);
        injector.add(SimpleRegistrationService.class);

        addUsecase(RegisterUsecase.class);
        addUsecase(LoginUsecase.class);
    }

    @Override
    public <T> T inject(final T entity, final Class<?> clazz) {
        injector.inject(entity, clazz);
        return entity;
    }

}
