package io.github.codejanovic.example.javalin;

import io.github.codejanovic.example.javalin.inject.InjectUsecaseExecutor;
import io.github.codejanovic.example.javalin.inject.Injectable;
import io.github.codejanovic.example.javalin.inject.InjectableProvider;
import org.jusecase.inject.Injector;

public class Assembler extends InjectUsecaseExecutor implements Injectable {

    public static final Assembler instance = new Assembler();

    Injector _injector = Injector.getInstance();

    @Override
    public <T> T inject(final T entity, final Class<?> clazz) {
        _injector.inject(entity, clazz);
        return entity;
    }


    private void addInjectable() {
        _injector.addProvider(InjectableProvider.class);
    }
}
