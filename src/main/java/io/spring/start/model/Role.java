package io.spring.start.model;

// import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

// menandakan bahwa role merupakan entitas dari tabel role
@Entity
@Table(name = "tb_m_role") // menandakan nama tabel untuk entity tersebut
public class Role {
  // menandakan bahwa id merupakan primary key
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  // menandakan column name nya
  @Column(name = "name", nullable = false, length = 100) // name merujuk ke dalam database
  private String nama; // utk digunakan di dalam program

  // membuat hubungan diantara tabel role dan user
  // @OneToMany(mappedBy = "User")
  // private Set<User> user;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getNama() {
    return this.nama;
  }
}
