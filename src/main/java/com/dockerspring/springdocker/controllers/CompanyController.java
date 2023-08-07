package com.dockerspring.springdocker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dockerspring.springdocker.model.Company;
import com.dockerspring.springdocker.services.CompanyService;

@Controller
@RequestMapping("/companies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @PostMapping
    public ResponseEntity<Company> save(@RequestBody Company company) {

        Optional<Company> optionalCompany = companyService.saveCompany(company);
		try {
			return ResponseEntity.ok(optionalCompany.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
        
    }
}
