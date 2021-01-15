package am.gitc.chat.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

  private Properties properties;

  private Settings() {
    try {
      this.loadProperties();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Settings getInstance() {
    return Helper.settings;
  }

  private void loadProperties() throws IOException {
    try (InputStream in = Settings.class.getClassLoader()
        .getResourceAsStream("application.properties")) {
      this.properties = new Properties();
      this.properties.load(in);
    }
  }

  public String getProperty(String key) {
    return this.properties.getProperty(key);
  }

  private static class Helper {
    private static final Settings settings = new Settings();
  }
}
