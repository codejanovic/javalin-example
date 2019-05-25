package io.github.codejanovic.example.javalin.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.codejanovic.example.javalin.auth.Secret;
import io.github.codejanovic.example.javalin.auth.token.DecodedToken;
import io.github.codejanovic.example.javalin.auth.token.Token;
import io.github.codejanovic.example.javalin.auth.user.User;
import io.github.codejanovic.example.javalin.chrono.DateProvider;
import io.github.codejanovic.example.javalin.errors.InternalServerError;
import io.github.codejanovic.example.javalin.misc.Text;
import io.github.codejanovic.example.javalin.repository.UserRepository;
import org.jusecase.inject.Component;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import static java.util.concurrent.TimeUnit.HOURS;

@Component
public class JwtAuthorizationService implements AuthorizationService {

    private static final String __issuer = "io.github.codejanovic.example.javalin";
    private final Algorithm _algorithm;
    @Inject
    private Secret _secret;
    @Inject
    private DateProvider _dateProvider;
    @Inject
    private UserRepository _userRepository;

    public JwtAuthorizationService() {
        _algorithm = createAlgorithm();
    }

    @Override
    public Token decode(Text bearer) {
        final DecodedJWT jwt = getDecodedJwt(bearer.asString());
        return new DecodedToken(jwt);
    }

    @Override
    public Optional<User> authorize(final Text bearer) {
        final Token token = decode(bearer);
        final Text userId = token.userId();
        return _userRepository.findById(userId);
    }


    private DecodedJWT getDecodedJwt(String token) {
        try {
            JWTVerifier verifier = JWT.require(_algorithm).withIssuer(__issuer).build();
            return verifier.verify(token);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Text encode(Token token) {
        final JWTCreator.Builder builder = JWT.create()
                .withIssuer(__issuer)
                .withExpiresAt(_dateProvider.future(HOURS, 4).asDate())
                .withClaim("userId", token.userId().asString());
        return new Text.CaseSensitive(builder.sign(_algorithm));
    }

    private Algorithm createAlgorithm() {
        try {
            return Algorithm.HMAC256(_secret.asString());
        } catch (UnsupportedEncodingException exception) {
            throw new InternalServerError("UTF-8 encoding not supported", exception);
        }
    }
}
