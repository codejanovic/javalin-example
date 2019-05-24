package io.github.codejanovic.example.javalin.chrono;

import java.util.concurrent.TimeUnit;

public class ChronoSeconds extends ChronoPoint {
    public ChronoSeconds(long duration) {
        super(TimeUnit.SECONDS, duration);
    }
}
