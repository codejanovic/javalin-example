package io.github.codejanovic.example.javalin;

import io.github.codejanovic.example.javalin.auth.Roles;
import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.errors.BadRequest;
import io.github.codejanovic.example.javalin.errors.NotFound;
import io.github.codejanovic.example.javalin.errors.ServiceUnavailable;
import io.github.codejanovic.example.javalin.errors.Unauthorized;
import io.github.codejanovic.example.javalin.inject.Injectable;
import io.github.codejanovic.example.javalin.misc.Text;
import io.github.codejanovic.example.javalin.routes.HelloAuthenticatedRoute;
import io.github.codejanovic.example.javalin.routes.LoginRoute;
import io.github.codejanovic.example.javalin.routes.RegisterRoute;
import io.github.codejanovic.example.javalin.service.AuthorizationService;
import io.javalin.Context;
import io.javalin.Javalin;
import io.javalin.UnauthorizedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Optional;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.security.SecurityUtil.roles;

public class Server {

    private static final Logger _log = LogManager.getLogger(Server.class);
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

        app.requestLogger((ctx, timeMs) -> {
            final String message = ctx.method() + " " + ctx.path() + " took " + timeMs + " ms -> " + ctx.status() + (ctx.status() >= 300 && !ctx.resultString().isEmpty() ? " - " + ctx.resultString() : "");
            if (ctx.status() >= 200 && ctx.status() < 400) {
                _log.info(message);
            } else if (ctx.status() >= 400 && ctx.status() < 500) {
                _log.warn(message);
            } else {
                _log.error(message);
            }

        });

        app.exception(Exception.class, (e, ctx) -> {
            if (e instanceof BadRequest) {
                ctx.status(400).result(e.getMessage());
            } else if (e instanceof NotFound) {
                ctx.status(404).result(e.getMessage());
            } else if (e instanceof ServiceUnavailable) {
                ctx.status(503).result(e.getMessage());
            } else if (e instanceof Unauthorized) {
                ctx.status(401).result(e.getMessage());
            } else {
                ctx.status(500).result(e.getMessage());
                _log.fatal("Oops, this was propably unintended", e);
            }
        });


        app.start();
        app.routes(() -> {
            post("/register", inject(new RegisterRoute(), RegisterRoute.class), roles(Roles.Public));
            post("/login", inject(new LoginRoute(), LoginRoute.class), roles(Roles.Public));
            get("/v1/hello", inject(new HelloAuthenticatedRoute(), HelloAuthenticatedRoute.class), roles(Roles.User));
        });

        app.before("/v1/*", this::authenticate);
    }

    private <T> T inject(T entity, Class<?> clazz) {
        return _injectable.inject(entity, clazz);
    }

    private void authenticate(Context ctx) {
       if (!getUserRole(ctx).isAuthenticated()) {
            throw new UnauthorizedResponse("");
       }
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

