package io.spring.start.model;

import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_user")
public class User {
  @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, length = 50)
  private String idUser;

  @Column(name = "username", nullable = false, length = 100)
  private String username;

  @Column(name = "password", nullable = false, length = 100)
  private String password;

  @ManyToOne
  // untuk menghubungkan tabel dengan relasi nya nama field
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  public void setId(String idUser) {
    this.idUser = idUser;
  }

  public String getId() {
    return this.idUser;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Role getRole() {
    return this.role;
  }
}
