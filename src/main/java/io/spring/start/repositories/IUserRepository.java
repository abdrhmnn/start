package io.spring.start.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.start.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

}
