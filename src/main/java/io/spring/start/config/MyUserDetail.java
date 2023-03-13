package io.spring.start.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.spring.start.model.User;
import io.spring.start.repositories.IManagementRepository;

@Service
public class MyUserDetail implements UserDetails, UserDetailsService {

  @Autowired
  private IManagementRepository managementRepository;
  private String username;
  private String password;
  private GrantedAuthority authority;

  public MyUserDetail() {
  }

  public MyUserDetail(User user) {
    this.username = user.getEmployee().getEmail();
    this.password = user.getPassword();
    this.authority = new SimpleGrantedAuthority(user.getRole().getName());
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    io.spring.start.model.User data = managementRepository.Login(username);
    return new MyUserDetail(data);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> grantedAuthority = new HashSet<>();
    grantedAuthority.add(authority);
    return grantedAuthority;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
