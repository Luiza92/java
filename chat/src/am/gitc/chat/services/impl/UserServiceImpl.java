package am.gitc.chat.services.impl;

import am.gitc.chat.models.User;
import am.gitc.chat.repositories.UserRepository;
import am.gitc.chat.repositories.impl.UserRepositorySql;
import am.gitc.chat.services.UserService;

import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl() {
    this.userRepository = new UserRepositorySql();
  }

  @Override
  public void save(User user) {
    try {
      this.userRepository.save(user);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<User> getByEmail(String email) {
    try {
      return this.userRepository.findByEmail(email);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public boolean userExist(String email) {
    try {
      return this.userRepository.userExist(email);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
