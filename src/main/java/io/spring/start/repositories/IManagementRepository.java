package io.spring.start.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.spring.start.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IManagementRepository extends JpaRepository<User, Integer> {
  // login
  // @Query(value = "SELECT new io.spring.start.dbo.Login() User u JOIN u.Employee
  // ON u.id == e.id JOIN u.Role r ON u.Role.id == r.id WHERE e.email == :email")
  @Query(value = "SELECT e.fullname, e.email, r.name, u.password FROM tb_m_employee e JOIN tb_m_role r ON e.role_id == r.id JOIN tb_m_user u ON e.id == u.id WHERE e.email == :email", nativeQuery = true)
  public io.spring.start.dto.Login Login(@Param("email") String email);
}
