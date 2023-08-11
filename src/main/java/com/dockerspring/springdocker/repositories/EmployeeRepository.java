package com.dockerspring.springdocker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockerspring.springdocker.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Employee findById(long id);

    Employee findByCpf(String cpf);

    Employee findByEmail(String email);

}
