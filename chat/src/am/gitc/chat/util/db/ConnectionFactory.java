package am.gitc.chat.util.db;

import am.gitc.chat.config.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  private ConnectionFactory() {
  }

  public static ConnectionFactory getInstance() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return Helper.CONNECTION_FACTORY;
  }

  public Connection getConnection() throws SQLException {
    String url = Settings.getInstance().getProperty("mysql.host");
    String user = Settings.getInstance().getProperty("mysql.user");
    String password = Settings.getInstance().getProperty("mysql.password");
    return DriverManager.getConnection(url, user, password);
  }

  private static class Helper {
    private static final ConnectionFactory CONNECTION_FACTORY = new ConnectionFactory();
  }
}
