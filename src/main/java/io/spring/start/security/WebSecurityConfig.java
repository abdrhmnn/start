package io.spring.start.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  // method dibawah untuk authorization user
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> {
          try {
            requests
                .antMatchers("/", "/user", "/role")
                // .hasRole("Manager")
                // .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        });
    // .formLogin((form) -> form
    // .loginPage("/login")
    // .permitAll())
    // .logout((logout) -> logout.permitAll());

    return http.build();
  }

  // @Bean
  // public UserDetailsService userDetailsService() {
  // UserDetails user = User.withDefaultPasswordEncoder()
  // .username("user")
  // .password("password")
  // .build();

  // return new InMemoryUserDetailsManager(user);
  // }
}
