package io.github.codejanovic.example.javalin.chrono;

import java.util.concurrent.TimeUnit;

public interface DateProvider {
    final class Default implements DateProvider {

        @Override
        public Chrono now() {
            return new ChronoNow();
        }

        @Override
        public Chrono future(TimeUnit hours, int duration) {
            return new ChronoFuture(hours, duration);
        }
    }

    Chrono now();

    Chrono future(TimeUnit hours, int duration);
}
