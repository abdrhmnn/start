package io.spring.start.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.spring.start.model.Role;
import io.spring.start.repositories.IRoleRepository;

@Service
public class RoleServiceImpl implements IRoleService {
  // LINE 12 - 17 NAMANYA DEPEDENCY INJECTION
  private IRoleRepository roleRepository;

  public RoleServiceImpl(IRoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public List<Role> get() {
    return roleRepository.findAll();
  }

  @Override
  public Role get(Integer id) {
    return roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("data tidak ditemukan!"));
  }

  @Override
  public Boolean save(Role role) {
    roleRepository.save(role);
    return roleRepository.findById(role.getId()).isPresent();
  }

  @Override
  public Boolean delete(Integer id) {
    roleRepository.deleteById(id);
    return roleRepository.findById(id).isPresent();
  }

}
