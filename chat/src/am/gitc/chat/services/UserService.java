package am.gitc.chat.services;

import am.gitc.chat.models.User;

import java.util.Optional;

public interface UserService {

  void save(User user);

  Optional<User> getByEmail(String email);

  boolean userExist(String email);
}
