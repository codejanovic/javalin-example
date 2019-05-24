package io.github.codejanovic.example.javalin.chrono;

import java.util.concurrent.TimeUnit;

public class ChronoPoint extends Chrono.Abstract {
    protected ChronoPoint(final TimeUnit timeUnit, final long duration) {
        super(timeUnit.toMillis(duration));
    }
}
