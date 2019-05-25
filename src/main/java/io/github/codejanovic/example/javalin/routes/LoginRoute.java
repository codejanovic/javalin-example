package io.github.codejanovic.example.javalin.routes;

import io.github.codejanovic.example.javalin.usecase.LoginUsecase;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

public class LoginRoute extends Route.Abstract {

    @Override
    protected void handleInternal(@NotNull final Context ctx) throws Exception {
        final LoginUsecase.LoginRequest usecaseRequest = ctx.bodyAsClass(LoginUsecase.LoginRequest.class);
        ctx.json((Object) _usecaseExecutor.execute(usecaseRequest));
    }
}
