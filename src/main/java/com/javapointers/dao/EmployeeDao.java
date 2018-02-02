package com.javapointers.dao;

import com.javapointers.bean.Employee;

import java.util.List;


public interface EmployeeDao {
    public List<Employee> getEmployees();
    public Employee getEmployee(int employeeId);
    public int deleteEmployee(int employeeId);
    public int updateEmployee(Employee employee);
    public int createEmployee(Employee employee);
}


