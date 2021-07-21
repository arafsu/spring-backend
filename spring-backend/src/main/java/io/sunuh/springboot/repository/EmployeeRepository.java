package io.sunuh.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.sunuh.springboot.entity.DAOEmployee;

@Repository
public interface EmployeeRepository extends JpaRepository<DAOEmployee, Long>{

}
