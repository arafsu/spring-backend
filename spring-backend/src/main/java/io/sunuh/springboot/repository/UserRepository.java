package io.sunuh.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.sunuh.springboot.entity.DAOUser;

@Repository
public interface UserRepository extends JpaRepository<DAOUser, Long>{
	DAOUser findByUsername(String username);
}
