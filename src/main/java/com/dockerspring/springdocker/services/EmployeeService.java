package com.dockerspring.springdocker.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.dockerspring.springdocker.model.Employee;
import com.dockerspring.springdocker.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        Employee employeeCpf = employeeRepository.findByCpf(employee.getCpf());

        if(employeeCpf != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        employee.setCreationDate(LocalDate.now());
        employee.setLastUpdate(LocalDateTime.now());
        employee.setPassword(employee.getPassword());
        employee.setCompany(employee.getCompany());
        employee.setCategory(employee.getCategory());

        return employeeRepository.save(employee);
    }
}
