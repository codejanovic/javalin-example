package io.github.codejanovic.example.javalin.misc;

import java.util.UUID;

public class UuidText extends Text.CaseInsensitive {

    public UuidText() {
        super(UUID.randomUUID().toString());
    }

    public UuidText(final String prefix) {
        super(prefix + "_" + UUID.randomUUID().toString());
    }
}
