package io.github.codejanovic.example.javalin;

import io.github.codejanovic.example.javalin.auth.Roles;
import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.errors.Unauthorized;
import io.github.codejanovic.example.javalin.inject.Injectable;
import io.github.codejanovic.example.javalin.misc.Text;
import io.github.codejanovic.example.javalin.routes.LoginRoute;
import io.github.codejanovic.example.javalin.routes.RegisterRoute;
import io.github.codejanovic.example.javalin.service.AuthorizationService;
import io.javalin.Context;
import io.javalin.Javalin;

import javax.inject.Inject;

import java.util.Optional;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.security.SecurityUtil.roles;

public class Server {

    private static final String __authorizationHeader = "Authorization";
    private static final String __bearer = "Bearer ";

    @Inject
    Injectable _injectable;
    @Inject
    AuthorizationService _authorizationService;

    public void start() {
        Javalin app = Javalin.create();

        // Set the access-manager that Javalin should use
        app.accessManager((handler, ctx, permittedRoles) -> {
            Roles userRole = getUserRole(ctx);
            if (permittedRoles.contains(userRole)) {
                handler.handle(ctx);
            } else {
                ctx.status(401).result("Unauthorized");
            }
        });

        app.start();
        app.routes(() -> {
            post("/register", inject(new RegisterRoute(), RegisterRoute.class), roles(Roles.Public));
            post("/login", inject(new LoginRoute(), LoginRoute.class), roles(Roles.Public));
            get("/v1/hello", ctx -> ctx.result("Hello"), roles(Roles.User));
        });

    }

    private <T> T inject(T entity, Class<?> clazz) {
        return _injectable.inject(entity, clazz);
    }

    private Roles getUserRole(Context ctx) {
        final Text authorizationHeader = new Text.CaseSensitive(ctx.req.getHeader(__authorizationHeader));
        if (authorizationHeader.isNullOrEmpty()) {
           return Roles.Public;
        }
        if (!authorizationHeader.startsWith(__bearer)) {
            return Roles.Public;
        }
        final String bearer = authorizationHeader.asString().substring(authorizationHeader.asString().indexOf(__bearer) + __bearer.length());
        final Optional<User> user = _authorizationService.authorize(new Text.CaseSensitive(bearer));
        if (user.isPresent()) {
            ctx.sessionAttribute("userId", user.get().email());
            return Roles.User;
        } else {
            return Roles.Public;
        }
    }

}

