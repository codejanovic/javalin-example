package io.github.codejanovic.example.javalin.inject;

public interface Injectable {
    <T> T inject(final T entity, Class<?> clazz);
}
