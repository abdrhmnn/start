package io.spring.start.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IManagementRepository {
  // login
  // @Query(value = "SELECT e.fullname, e.email, u.password, r.name FROM
  // tb_m_employee e JOIN tb_m_user u ON e.id = u.id JOIN tb_m_role r ON u.role_id
  // = r.id WHERE e.email = :email", nativeQuery = true)
  @Query("""
      SELECT new io.spring.start.dto.Login() User u JOIN u.Employee e ON u.id
      == e.id JOIN u.Role r ON u.Role.id == r.id WHERE e.email == :email
      """)
  public io.spring.start.dto.Login Login(@Param("email") String email);
}
