package com.dockerspring.springdocker.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Employee findById(long id) {
        return employeeRepository.findById(id);
    }

    public Employee findByCpf(String cpf) {
        return employeeRepository.findByCpf(cpf);
    }

    public Employee saveEmployee(Employee employee) {
        Employee employeeByCpf = employeeRepository.findByCpf(employee.getCpf());
        Employee employeeByEmail = employeeRepository.findByEmail(employee.getEmail());

        if(employeeByCpf != null || employeeByEmail != null ) {
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
        Employee employeeByCpf = employeeRepository.findByCpf(updatedEmployee.getCpf());

        if(employeeDatabase == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else if(!updatedEmployee.getCpf().equals(employeeDatabase.getCpf()) && employeeByCpf != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeDatabase;

        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setCpf(updatedEmployee.getCpf());
        employee.setPassword(updatedEmployee.getPassword());
        employee.setSalary(updatedEmployee.getSalary());

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
