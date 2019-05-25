package io.github.codejanovic.example.javalin.routes;

import io.github.codejanovic.example.javalin.usecase.RegisterUsecase;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

public class RegisterRoute extends Route.Abstract {

    @Override
    protected void handleInternal(@NotNull final Context ctx) throws Exception {
        final RegisterUsecase.RegisterRequest usecaseRequest = ctx.bodyAsClass(RegisterUsecase.RegisterRequest.class);
        ctx.json((Object) _usecaseExecutor.execute(usecaseRequest));
    }
}
