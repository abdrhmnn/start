package io.spring.start.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.spring.start.model.Employee;
import io.spring.start.model.User;

@Repository
public interface IManagementRepository extends JpaRepository<Employee, Integer> {
  // login
  // @Query(value = "SELECT e.fullname, e.email, u.password FROM tb_m_employee e
  // JOIN tb_m_user u ON e.id = u.id WHERE e.email = :email", nativeQuery = true)
  // @Query("""
  // select
  // new io.spring.start.dto.Login() User u
  // JOIN u.Employee e ON u.id == e.id
  // WHERE e.email == :email
  // """)

  // tanda ? berfungsi untuk mapping parameter yg sesuai dgn urutannya
  @Query(value = "select u, r from User u join u.Employee e join u.Role r where email = ?1")
  public User Login(String email);
}
