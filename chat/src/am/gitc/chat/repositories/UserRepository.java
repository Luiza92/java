package am.gitc.chat.repositories;

import am.gitc.chat.models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserRepository {

  void save(User user) throws SQLException;

  Optional<User> findByEmail(String email) throws SQLException;

  boolean userExist(String email) throws SQLException;

}
