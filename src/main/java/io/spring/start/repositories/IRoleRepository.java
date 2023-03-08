package io.spring.start.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.start.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

}
