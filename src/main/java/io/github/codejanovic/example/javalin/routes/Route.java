package io.github.codejanovic.example.javalin.routes;

import io.github.codejanovic.example.javalin.Assembler;
import io.github.codejanovic.example.javalin.errors.BadRequest;
import io.github.codejanovic.example.javalin.errors.InternalServerError;
import io.github.codejanovic.example.javalin.errors.NotFound;
import io.github.codejanovic.example.javalin.errors.UsecaseError;
import io.javalin.BadRequestResponse;
import io.javalin.Context;
import io.javalin.Handler;
import org.jetbrains.annotations.NotNull;
import org.jusecase.Usecase;
import org.jusecase.UsecaseExecutor;

public interface Route extends Handler {
    abstract class Abstract implements Route {

        protected final UsecaseExecutor _usecaseExecutor;

        public Abstract() {
            _usecaseExecutor = Assembler.instance;
        }

        @Override
        public String getName() {
            return getClass().getSimpleName();
        }

        @Override
        public void handle(@NotNull final Context ctx) throws Exception {
                handleInternal(ctx);
        }

        protected abstract void handleInternal(@NotNull final Context ctx) throws Exception;


    }

    String getName();
}
