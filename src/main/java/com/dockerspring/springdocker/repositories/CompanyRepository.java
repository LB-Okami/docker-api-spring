package com.dockerspring.springdocker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockerspring.springdocker.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
    List<Company> findAll();
}
