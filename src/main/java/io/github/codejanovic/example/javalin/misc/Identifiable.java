package io.github.codejanovic.example.javalin.misc;


/**
 * identifies a entity by its unique identifier,
 * overriding equals and hashcode
 */
public interface Identifiable {

    abstract class Abstract implements Identifiable {
        private final Text _identifier;

        protected Abstract(final Text identifier) {
            _identifier = identifier;
        }

        @Override
        public Text identifier() {
            return _identifier;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || o.getClass().isAssignableFrom(Identifiable.class)) return false;

            final Identifiable that = (Identifiable) o;

            return _identifier.equals(that.identifier());
        }

        @Override
        public int hashCode() {
            return _identifier.hashCode();
        }
    }

    Text identifier();
}
