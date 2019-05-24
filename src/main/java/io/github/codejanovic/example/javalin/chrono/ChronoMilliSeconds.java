package io.github.codejanovic.example.javalin.chrono;

import java.util.concurrent.TimeUnit;

public class ChronoMilliSeconds extends ChronoPoint {
    public ChronoMilliSeconds(long duration) {
        super(TimeUnit.MILLISECONDS, duration);
    }
}
