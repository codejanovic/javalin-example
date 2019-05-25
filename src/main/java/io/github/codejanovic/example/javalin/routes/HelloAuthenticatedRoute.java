package io.github.codejanovic.example.javalin.routes;

import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.service.AuthorizationService;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.Optional;

public class HelloAuthenticatedRoute extends Route.Abstract {

    @Inject
    AuthorizationService _authorizationService;

    @Override
    protected void handleInternal(@NotNull final Context ctx) throws Exception {
        final Optional<User> user = _authorizationService.get(ctx.sessionAttribute("userId"));
        ctx.json(user.get().mutable());
    }
}
