package io.spring.start.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.spring.start.model.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
  @Query(value = "SELECT e.id, e.fullname, e.email, u.password, r.name FROM tb_m_employee e JOIN tb_m_user u ON e.id = u.id JOIN tb_m_role r ON u.role_id = r.id", nativeQuery = true)
  public List<Employee> getAllUser();
}
