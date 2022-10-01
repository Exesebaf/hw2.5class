package com.compamy.hwclass.service;


import com.compamy.hwclass.exception.EmployeeAlreadyAddedException;
import com.compamy.hwclass.exception.EmployeeNotFondException;
import com.compamy.hwclass.exception.EmployeeStorageIsFullException;
import com.compamy.hwclass.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    private static final int SIZE = 5;
    private final List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    public Employee add(String firsName,
                        String lastName) {
        Employee employee = new Employee(firsName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() >= SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        employees.add(employee);
        return employee;

    }

    public Employee remove(String firsName, String lastName) {
        Employee employee = new Employee(firsName, lastName);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFondException();
    }

    public Employee find(String firsName, String lastName) {
        Employee employee = new Employee(firsName, lastName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFondException();
    }


    public List<Employee> findAll() {
        return Collections.unmodifiableList(employees);
    }
}
