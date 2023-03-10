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

import io.spring.start.dto.Login;
import io.spring.start.repositories.IManagementRepository;

// untuk get data
public class MyUserDetail implements UserDetails, UserDetailsService {

  @Autowired
  private IManagementRepository managementRepository;

  private String username;
  private String password;
  private GrantedAuthority authority;

  public MyUserDetail() {
    super();
  }

  public MyUserDetail(Login user) {
    username = user.getEmail();
    password = user.getPassword();
    authority = new SimpleGrantedAuthority(user.getRole());
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // object data akan di passing ke constructor myuserdetails
    io.spring.start.dto.Login data = managementRepository.Login(username);
    return new MyUserDetail(data);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add(authority);
    return getAuthorities();
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
