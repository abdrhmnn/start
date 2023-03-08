package io.spring.start.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.spring.start.model.User;
import io.spring.start.repositories.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {
  private IUserRepository userRepository;

  public UserServiceImpl(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> get() {
    return userRepository.findAll();
  }

  @Override
  public User get(String id) {
    return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("data tidak ditemukan!"));
  }

  @Override
  public Boolean save(User user) {
    userRepository.save(user);
    return userRepository.findById(user.getId()).isPresent();
  }

  @Override
  public Boolean delete(String id) {
    userRepository.deleteById(id);
    return userRepository.findById(id).isPresent();
  }
}
