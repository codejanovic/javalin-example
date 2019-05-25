package io.github.codejanovic.example.javalin.misc;


import java.nio.charset.StandardCharsets;

public interface Text {

    Text empty = new CaseInsensitive(null);

    abstract class Abstract implements Text {
        protected final String _value;

        public Abstract(final String value) {
            _value = value;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || o.getClass().isAssignableFrom(Text.class)) return false;

            final Text that = (Text) o;

            return isEqualTo(that.asString());
        }

        abstract protected boolean isEqualTo(final String other);

        @Override
        abstract public int hashCode();


        @Override
        public String asString() {
            return _value;
        }
    }

    class CaseSensitive extends Abstract {

        public CaseSensitive(final String value) {
            super(value);
        }

        @Override
        protected boolean isEqualTo(final String other) {
            return _value != null ? _value.equals(other) : other == null;
        }

        @Override
        public int hashCode() {
            return _value.hashCode();
        }

        @Override
        public boolean startsWith(final String text) {
            if (!isPresent() ) {
                return false;
            }
            return asString().startsWith(text);
        }

        @Override
        public boolean endsWith(final String text) {
            if (!isPresent() ) {
                return false;
            }
            return asString().endsWith(text);
        }

        @Override
        public boolean contains(final String text) {
            if (!isPresent() ) {
                return false;
            }
            return asString().contains(text);
        }
    }

    class CaseInsensitive extends Abstract {

        public CaseInsensitive(final String value) {
            super(value);
        }

        @Override
        protected boolean isEqualTo(final String other) {
            return _value != null ? _value.equalsIgnoreCase(other) : other == null;
        }

        @Override
        public int hashCode() {
            return _value.toLowerCase().hashCode();
        }

        @Override
        public boolean startsWith(final String text) {
            if (!isPresent() ) {
                return false;
            }
            return asString().toLowerCase().startsWith(text.toLowerCase());
        }

        @Override
        public boolean endsWith(final String text) {
            if (!isPresent() ) {
                return false;
            }
            return asString().toLowerCase().endsWith(text.toLowerCase());
        }

        @Override
        public boolean contains(final String text) {
            if (!isPresent() ) {
                return false;
            }
            return asString().toLowerCase().contains(text.toLowerCase());
        }
    }

    String asString();

    default boolean isNullOrEmpty() {
        return asString() == null || (asString().trim().isEmpty());
    }

    default boolean isNull() {
        return !isPresent();
    }
    default boolean isPresent() {
        return asString() != null;
    }

    boolean startsWith(String text);

    boolean endsWith(String text);

    boolean contains(String text);

    default byte[] asBytes() {
        if (isNullOrEmpty()) {
            return new byte[0];
        }
        return asString().getBytes(StandardCharsets.UTF_8);
    }
}
