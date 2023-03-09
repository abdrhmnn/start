package io.spring.start.services;

import java.util.List;

import io.spring.start.model.User;

public interface IUserService {
  public List<User> get();

  public User get(String id);

  public Boolean save(User user);

  public Boolean delete(String id);

  public List<User> getAllByRole(Integer id);

  // get partial data using dto
  public List<io.spring.start.dto.User> getUsernameAndPassword(Integer role_id);

  // get data with native query
  public List<User> getDataWithNativeQuery(Integer role_id);
}
