package io.github.codejanovic.example.javalin.chrono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public interface Chrono {

    abstract class Abstract implements Chrono {
        private final long _originalInMillis;
        private final long _originalInSeconds;

        protected Abstract(final long originalInMillis) {
            _originalInMillis = originalInMillis;
            _originalInSeconds = _originalInMillis / 1000;
        }

        @Override
        public long inSeconds() {
            return _originalInSeconds;
        }

        @Override
        public long inMillis() {
            return _originalInMillis;
        }
    }

    long inSeconds();

    long inMillis();

    default Date asDate() {
        return new Date(inMillis());
    }

    default LocalDateTime asLocalDate() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(inMillis()), ZoneId.systemDefault());
    }
}
