package finalproject.utils;

import finalproject.domain.entities.user.Role;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Validate;

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

  public void validateName(String name) {
    if (!Validators.notNullString(name)) {
      problems.add("name");
    }
  }

  public void validateRole(Role role) {
      try {
        Validate.notNull(role);
      }
      catch (Exception e) {
        problems.add("role");
      }
  }




}
