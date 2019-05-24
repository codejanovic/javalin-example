package io.github.codejanovic.example.javalin.chrono;

import java.util.concurrent.TimeUnit;

public final class ChronoFuture extends Chrono.Abstract {

    public ChronoFuture(TimeUnit timeUnit, int duration) {
        this(timeUnit.toMillis(duration));
    }

    public ChronoFuture(long offsetInMillis) {
        super(System.currentTimeMillis() + offsetInMillis);
    }
}
