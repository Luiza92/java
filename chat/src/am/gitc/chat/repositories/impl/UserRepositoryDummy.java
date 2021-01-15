package am.gitc.chat.repositories.impl;

import am.gitc.chat.models.User;
import am.gitc.chat.repositories.UserRepository;
import am.gitc.chat.util.DataValidator;

import java.io.*;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class UserRepositoryDummy implements UserRepository {

  private static final Map<String, User> STORAGE = new ConcurrentHashMap<>();

  public UserRepositoryDummy() {
    try {
      loadUsers();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void save(User user) {
    STORAGE.put(user.getEmail(), user);
    try {
      saveIntoFile(user);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return Optional.ofNullable(STORAGE.get(email));
  }

  @Override
  public boolean userExist(String email) {
    return STORAGE.containsKey(email);
  }

  private static void saveIntoFile(User user) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
      writer.newLine();
      writer.write(user.getFirstName());
      writer.write("\t");
      writer.write(user.getLastName());
      writer.write("\t");
      writer.write(user.getEmail());
      writer.write("\t");
      writer.write(user.getPassword());
    }
  }

  private static void loadUsers() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
      for (String ud : reader.lines().collect(Collectors.toList()))
        if (!DataValidator.isNullOrEmpty(ud)) {
          String[] values = ud.split("\t");
          User user = new User();
          user.setFirstName(values[0]);
          user.setLastName(values[1]);
          user.setEmail(values[2]);
          user.setPassword(values[3]);
          STORAGE.put(user.getEmail(), user);
        }
    }
  }
}
