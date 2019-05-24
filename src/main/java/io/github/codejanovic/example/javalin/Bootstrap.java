package io.github.codejanovic.example.javalin;

public class Bootstrap {

    public static void main(String[] args) {
        final Server server = new Server();
        Assembler.instance.inject(server, Server.class);
        server.start();
    }
}
