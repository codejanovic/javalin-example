package io.github.codejanovic.example.javalin.auth.token;


import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.codejanovic.example.javalin.misc.Text;

public class DecodedToken implements Token {
    private final Text _userId;

    public DecodedToken(final DecodedJWT jwt) {
        final Claim claim = jwt == null ? null : jwt.getClaim("userId");
        _userId = new Text.CaseInsensitive(claim == null ? "" : claim.asString());
    }

    @Override
    public Text userId() {
        return _userId;
    }
}
