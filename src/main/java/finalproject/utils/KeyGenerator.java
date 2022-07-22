package finalproject.utils;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;

/**
 * Утилита для создания ключа.
 */
public class KeyGenerator {

  /**
   * Метод для создания ключа.
   */
  public static void generateStringKey() {
    SecretKey key = Keys.secretKeyFor(HS256);
    String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
    System.out.println(encodedKey);

  }
}
