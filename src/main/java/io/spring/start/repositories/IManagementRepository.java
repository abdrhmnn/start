package io.spring.start.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IManagementRepository {
  // login
  @Query(value = "SELECT new io.spring.start.dto.Login() User u JOIN u.Employee e ON u.id == e.id JOIN u.Role r ON u.Role.id == r.id WHERE e.email == :email")
  public io.spring.start.dto.Login Login(@Param("email") String username);
}
