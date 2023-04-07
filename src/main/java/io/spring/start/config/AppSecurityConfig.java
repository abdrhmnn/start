package io.spring.start.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {

  // authentication
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  // authorization
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
      throws Exception {
    httpSecurity.csrf().disable().authorizeRequests(request -> {
      try {
        request.antMatchers("/user/**").permitAll()
            .antMatchers("/employee/**").hasAuthority("Administrator")
            .antMatchers("/role/**").hasAuthority("User")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/user/login")
            .and()
            .httpBasic()
            .and()
            .logout()
            .logoutSuccessUrl("/user/login");
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    return httpSecurity.build();
  }

  // encrypt
  @Bean
  public PasswordEncoder passwordEncoder() {

    // BCrypt yaitu proses hashing dari text ke cyper text
    // salah satu fitur di BCrypt yaitu SALT (kunci) utk mengubah hasil dari hashing
    // nya berbeda - beda
    return new BCryptPasswordEncoder();
  }
}
