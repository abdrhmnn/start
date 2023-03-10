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
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().authorizeRequests(request -> {
      try {
        request.antMatchers("/user/**").permitAll()
            .antMatchers("/role/**").hasAuthority("Administrator")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/user/login/")
            .and()
            .httpBasic()
            .and()
            .logout();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    return httpSecurity.build();
  }

  // encrypt
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
