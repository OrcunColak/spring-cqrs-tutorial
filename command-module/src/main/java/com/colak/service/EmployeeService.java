package com.colak.service;

import com.colak.common.jpa.Employee;
import com.colak.common.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final KafkaProducer kafkaProducer;


    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        kafkaProducer.sendMessage("Employee created with ID: " + savedEmployee.getId());
        return savedEmployee;
    }
}
