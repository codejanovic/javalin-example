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
    }


    class Unregistered extends AbstractEmail {

        public Unregistered(final String email) {
            super(email);
        }

    }
}
