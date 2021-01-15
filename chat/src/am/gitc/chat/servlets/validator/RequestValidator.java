package am.gitc.chat.servlets.validator;

import java.util.HashMap;
import java.util.Map;

public class RequestValidator<T> {

  private T value;
  private Map<String, String> errors = new HashMap<>();

  public boolean isValid() {
    return errors.isEmpty();
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public Map<String, String> getErrors() {
    return errors;
  }

  public void addError(String key, String value) {
    this.errors.put(key, value);
  }
}
