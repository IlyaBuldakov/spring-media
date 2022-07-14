package finalproject.utils;

import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Validators {
  static final String EMAIL_PATTERN = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
  static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";
  public static boolean patternValidate(String value, String pattern) {
    try {
      Validate.matchesPattern(value, pattern);
    }
    catch(Exception e){
      return false;
    }
    return true;
  }

  public static boolean notNullString(String value) {
    try {
      Validate.isTrue(value.length() > 0);
    }
    catch(Exception e){
      return false;
    }
    return true;
  }



  public List<String> problems = new ArrayList<>();

  public void validateEmail(String email) {
    if (!patternValidate(email, EMAIL_PATTERN)) {
      problems.add("email");
    }
  }
  public void validatePassword(String password) {
    if(!patternValidate(password, PASSWORD_PATTERN)) {
      problems.add("password");
    }

  }

  public void validateNonNullString(String value, String field) {
    if (!Validators.notNullString(value)) {
      problems.add(field);
    }
  }

  public void validateNotNull(Object object, String field) {
      try {
        Validate.notNull(object);
      }
      catch (Exception e) {
        problems.add(field);
      }
  }

  public void validateBase64(String image) {
    try {
      Validate.isTrue(Base64.isBase64(image) && Validators.notNullString(image));
    } catch (Exception e) {
      problems.add("image");
    }
  }

  public LocalDateTime validateDateTime(String date) {
    LocalDateTime validated;
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      validated = LocalDateTime.parse(date, formatter);
    } catch (Exception e) {
      problems.add("dateExpired");
      return null;
    }
    if (validated.isBefore(LocalDateTime.now())) {
      problems.add("dateExpired");
      return null;
    }
    return validated;
  }


}
