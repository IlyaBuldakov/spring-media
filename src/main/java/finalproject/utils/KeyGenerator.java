package finalproject.utils;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import java.util.Base64;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

public class KeyGenerator {
  public static void generateStringKey() {
    SecretKey key = Keys.secretKeyFor(HS256);
    String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
    System.out.println(encodedKey);

  }
}
