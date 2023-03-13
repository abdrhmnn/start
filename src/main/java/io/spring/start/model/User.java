package io.spring.start.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_user")
public class User {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "password", nullable = true, length = 100)
  private String password;

  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role Role;

  @OneToOne
  @JoinColumn(name = "id", nullable = false)
  private Employee Employee;

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return this.id;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }

  public Employee getEmployee() {
    return Employee;
  }

  public void setEmployee(Employee employee) {
    this.Employee = employee;
  }

  public Role getRole() {
    return Role;
  }

  public void setRole(Role role) {
    this.Role = role;
  }
}
