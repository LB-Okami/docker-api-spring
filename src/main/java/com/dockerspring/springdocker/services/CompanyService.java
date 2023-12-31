package com.dockerspring.springdocker.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dockerspring.springdocker.model.Company;
import com.dockerspring.springdocker.repositories.CompanyRepository;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(long id) {
        Company companyById = companyRepository.findById(id);

        if(companyById == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return companyRepository.findById(id);
    }

    public Company saveCompany(Company company) {
        Company companyByName = companyRepository.findByName(company.getName());

        if(companyByName != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        company.setCreationDate(LocalDate.now());
        company.setLastUpdate(LocalDateTime.now());

        return companyRepository.save(company);
    }

    public Company updateCompany(Company updatedCompany, long id) {

        Company companyDatabase = companyRepository.findById(id);
        Company companyByName = companyRepository.findByName(updatedCompany.getName());

        if(companyDatabase == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else if(!updatedCompany.getName().equals(companyDatabase.getName()) && companyByName != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Company company = companyDatabase;

        company.setName(updatedCompany.getName());
        company.setCreationDate(companyDatabase.getCreationDate());
        company.setLastUpdate(LocalDateTime.now());
        company.setEmployee(company.getEmployee());

        return companyRepository.save(company);
    }

    public void delete(Long id) {

        Optional<Company> companyById = companyRepository.findById(id);


        if(!companyById.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        companyRepository.deleteById(id);
    }
}
