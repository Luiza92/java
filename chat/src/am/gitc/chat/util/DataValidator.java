package am.gitc.chat.util;

public class DataValidator {

  private DataValidator() {
    throw new SecurityException("Cant instantiate Utility class.");
  }

  public static boolean isNullOrEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }
}
