package io.github.codejanovic.example.javalin.chrono;

public class ChronoNow extends Chrono.Abstract {

    public ChronoNow() {
        super(System.currentTimeMillis());
    }

}
