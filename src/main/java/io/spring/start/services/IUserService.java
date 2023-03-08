package io.spring.start.services;

import java.util.List;

import io.spring.start.model.User;

public interface IUserService {
  public List<User> get();

  public User get(String id);

  public Boolean save(User user);

  public Boolean delete(String id);
}
