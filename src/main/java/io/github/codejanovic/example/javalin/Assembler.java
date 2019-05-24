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
import org.jusecase.inject.Injector;

public class Assembler extends InjectUsecaseExecutor implements Injectable {

    public static final Assembler instance = new Assembler();

    Injector _injector = Injector.getInstance();


    public Assembler() {
        _injector.add(Secret.class);
        _injector.add(new DateProvider.Default());

        _injector.add(InMemoryUserRepository.class);

        _injector.add(JwtAuthorizationService.class);
        _injector.add(JwtLoginService.class);

        _injector.add(SimpleRegistrationService.class);
    }

    @Override
    public <T> T inject(final T entity, final Class<?> clazz) {
        _injector.inject(entity, clazz);
        return entity;
    }


    private void addInjectable() {
        _injector.addProvider(InjectableProvider.class);
    }
}
