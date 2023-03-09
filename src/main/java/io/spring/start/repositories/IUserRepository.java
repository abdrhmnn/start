package io.spring.start.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.spring.start.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

  // contoh penggunaan @Query JPQL
  // query di JPQL itu case sensitive dan nama database nya harus sesuai
  // dgn model di program

  @Query(value = "SELECT u FROM User u WHERE u.role.id = :role_id")
  public List<User> getAllByRole(@Param("role_id") Integer id);

  // jika ingin menampilkan sebagian field dari sebuah tabel, misal
  // dlm tabel user ada id, username, password dan role
  // trs kita hanya ingin menampilkan username dan password aja menggunakan
  // JPQL maka bisa menggunakan cara DTO (Data Transfer Object)
  // caranya :
  // - buat file DTO nya dulu, isinya seperti model ada
  // constructor, setter dan getter
  // - panggil objek dto nya di string Query dan masukan data yg dimasukkan
  // data yg dibutuhkan
  // - panggil DTO nya di generic List nya
  @Query(value = "SELECT new io.spring.start.dto.User(u.username) FROM User u WHERE u.role.id = :role_id")
  public List<io.spring.start.dto.User> getUsernameAndPassword(@Param("role_id") Integer role_id);

  // Native Query SQL
  @Query(value = "SELECT * FROM tb_m_user WHERE role_id = :role_id", nativeQuery = true)
  public List<User> getAllUserWithNativeQuery(@Param("role_id") Integer role_id);
}
