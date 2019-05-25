package io.github.codejanovic.example.javalin.auth.email;

import io.github.codejanovic.example.javalin.misc.Identifiable;
import io.github.codejanovic.example.javalin.misc.Text;

public interface Email extends Identifiable, Text {


    abstract class AbstractEmail extends Identifiable.Abstract implements Email {

        protected AbstractEmail(final String email) {
            super(new Text.CaseInsensitive(email.toLowerCase()));
        }

        @Override
        public String asString() {
            return identifier().asString();
        }

        @Override
        public boolean startsWith(final String text) {
            return identifier().startsWith(text);
        }

        @Override
        public boolean endsWith(final String text) {
            return identifier().endsWith(text);
        }

        @Override
        public boolean contains(final String text) {
            return identifier().contains(text);
        }
    }


    class Unregistered extends AbstractEmail {

        public Unregistered(final String email) {
            super(email);
        }

    }
}
