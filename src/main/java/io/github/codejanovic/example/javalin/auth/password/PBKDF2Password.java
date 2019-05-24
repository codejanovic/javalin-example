package io.github.codejanovic.example.javalin.auth.password;


import io.github.codejanovic.example.javalin.misc.Text;
import io.github.codejanovic.example.javalin.misc.UuidText;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public final class PBKDF2Password extends AbstractPassword {


    public PBKDF2Password(Text password) {
        this(password, new UuidText());
    }

    public PBKDF2Password(Text password, Text salt) {
        super(computeHash(salt.asBytes(), password.asString()), salt.asString());
    }

    private static String computeHash(byte[] salt, String password) {
        int iterations = 1000;
        char[] chars = password.toCharArray();

        final PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        final SecretKeyFactory skf;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = skf.generateSecret(spec).getEncoded();
            return iterations + ":" + toHex(salt) + ":" + toHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}
