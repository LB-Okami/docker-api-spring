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
import com.dockerspring.springdocker.model.Employee;
import com.dockerspring.springdocker.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(long id) {
        return employeeRepository.findById(id);
    }

    public Employee findByCpf(String cpf) {
        return employeeRepository.findByCpf(cpf);
    }

    public Employee saveEmployee(Employee employee) {
        Employee employeeCpf = employeeRepository.findByCpf(employee.getCpf());
        Employee employeeEmail = employeeRepository.findByEmail(employee.getEmail());

        if(employeeCpf != null || employeeEmail != null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        employee.setCreationDate(LocalDate.now());
        employee.setLastUpdate(LocalDateTime.now());
        employee.setCompany(employee.getCompany());
        employee.setCategory(employee.getCategory());

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee updatedEmployee, long id) {

        Employee employeeDatabase = employeeRepository.findById(id);

        if(employeeDatabase == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeDatabase;

        employee.setName(employeeDatabase.getName());
        employee.setCreationDate(employeeDatabase.getCreationDate());
        employee.setLastUpdate(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

    public void delete(Long id) {

        Optional<Employee> employeeId = employeeRepository.findById(id);


        if(!employeeId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        employeeRepository.deleteById(id);
    }
}
