package io.github.codejanovic.example.javalin;

import io.github.codejanovic.example.javalin.auth.Roles;
import io.javalin.Context;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.security.SecurityUtil.roles;

public class Server {

    public static void main(String[] args) {
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
            get("/un-secured", ctx -> ctx.result("Hello"), roles(Roles.Public));
            get("/secured", ctx -> ctx.result("Hello"), roles(Roles.User));
        });
    }

    private static Roles getUserRole(Context ctx) {
        // determine user role based on request
        // typically done by inspecting headers
        return Roles.Public;
    }

}

