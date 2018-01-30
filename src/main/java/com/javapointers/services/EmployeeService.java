package com.javapointers.services;


import java.util.List;

import com.javapointers.bean.Employee;

public interface EmployeeService {
    public List<Employee> getEmployees();
    public Employee getEmployee(int employeeId);
    public int deleteEmployee(int employeeId);
    public int updateEmployee(Employee employee);
    public int createEmployee(Employee employee);
}