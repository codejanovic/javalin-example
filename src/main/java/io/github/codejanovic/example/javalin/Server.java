package io.github.codejanovic.example.javalin;

import io.github.codejanovic.example.javalin.auth.Roles;
import io.github.codejanovic.example.javalin.inject.Injectable;
import io.github.codejanovic.example.javalin.routes.LoginRoute;
import io.github.codejanovic.example.javalin.routes.RegisterRoute;
import io.javalin.Context;
import io.javalin.Javalin;

import javax.inject.Inject;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.security.SecurityUtil.roles;

public class Server {

    @Inject
    Injectable _injectable;

    public void start() {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.result("Hello World"));

        // Set the access-manager that Javalin should use
        app.accessManager((handler, ctx, permittedRoles) -> {
            Roles userRole = getUserRole(ctx);
            if (permittedRoles.contains(userRole)) {
                handler.handle(ctx);
            } else {
                ctx.status(401).result("Unauthorized");
            }
        });

        app.routes(() -> {
            get("/register", inject(new RegisterRoute(), RegisterRoute.class), roles(Roles.Public));
            get("/login", inject(new LoginRoute(), LoginRoute.class), roles(Roles.Public));
            get("/secured", ctx -> ctx.result("Hello"), roles(Roles.User));
        });
    }

    private <T> T inject(T entity, Class<?> clazz) {
        return _injectable.inject(entity, clazz);
    }

    private static Roles getUserRole(Context ctx) {
        // determine user role based on request
        // typically done by inspecting headers
        return Roles.Public;
    }

}

