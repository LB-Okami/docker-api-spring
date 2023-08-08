package com.dockerspring.springdocker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockerspring.springdocker.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

    Company findByName(String name);

    Company findById(long id);

}
