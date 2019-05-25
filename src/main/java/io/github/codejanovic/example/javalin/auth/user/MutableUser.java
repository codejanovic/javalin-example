package io.github.codejanovic.example.javalin.auth.user;

public class MutableUser {
    private String id;
    private String email;

    public MutableUser(final String id, final String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public MutableUser setId(final String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MutableUser setEmail(final String email) {
        this.email = email;
        return this;
    }
}
