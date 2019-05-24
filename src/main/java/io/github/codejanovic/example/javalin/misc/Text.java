package io.github.codejanovic.example.javalin.misc;


import java.nio.charset.StandardCharsets;

public interface Text {

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
        public int hashCode() {
            return _value.hashCode();
        }


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
    }

    class CaseInsensitive extends Abstract {

        public CaseInsensitive(final String value) {
            super(value);
        }

        @Override
        protected boolean isEqualTo(final String other) {
            return _value != null ? _value.equalsIgnoreCase(other) : other == null;
        }
    }

    String asString();

    default byte[] asBytes() {
        return asString().getBytes(StandardCharsets.UTF_8);
    }
}
