package com.dockerspring.springdocker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockerspring.springdocker.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

    Optional<Company> findByName(String name);

}
