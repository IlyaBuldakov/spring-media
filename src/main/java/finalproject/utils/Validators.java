package finalproject.utils;

import org.apache.commons.lang3.Validate;

public class Validators {
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



}
