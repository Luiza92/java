package am.gitc.chat.repositories.impl;

import am.gitc.chat.models.User;
import am.gitc.chat.repositories.UserRepository;
import am.gitc.chat.util.db.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UserRepositorySql implements UserRepository {


  @Override
  public void save(User user) throws SQLException {
    try (Connection connection = ConnectionFactory.getInstance().getConnection();
         Statement statement = connection.createStatement()) {
      String query = String.format("INSERT INTO users(first_name, last_name, email, password)" +
              " VALUES('%s', '%s', '%s', '%s')",
          user.getFirstName(), user.getLastName(),
          user.getEmail(), user.getPassword());
      statement.execute(query);
    }
  }

  @Override
  public Optional<User> findByEmail(String email) throws SQLException {
    String query = String.format("SELECT * FROM users WHERE " +
        "email = '%s'", email);
    try (Connection connection = ConnectionFactory.getInstance().getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {
      if (resultSet.next()) {
        User user = new User();
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        return Optional.of(user);
      }
      return Optional.empty();
    }
  }

  @Override
  public boolean userExist(String email) throws SQLException {
    String query = String.format("SELECT count(*) FROM users WHERE " +
        "email = '%s'", email);
    try (Connection connection = ConnectionFactory.getInstance().getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {
      resultSet.next();
      return resultSet.getInt(1) == 1;
    }
  }
}
