package com.dockerspring.springdocker.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dockerspring.springdocker.model.Company;
import com.dockerspring.springdocker.repositories.CompanyRepository;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Optional<Company> saveCompany(Company company) {
        return Optional.of(companyRepository.save(company));
    }
}
