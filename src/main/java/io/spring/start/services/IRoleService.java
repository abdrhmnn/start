package io.spring.start.services;

import java.util.List;

import io.spring.start.model.Role;

public interface IRoleService {
  public List<Role> get();

  public Role get(Integer id);

  public Boolean save(Role role);

  public Boolean delete(Integer id);
}
