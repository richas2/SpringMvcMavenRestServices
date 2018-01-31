package com.javapointers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javapointers.bean.Employee;
import com.javapointers.dao.EmployeeDao;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao=employeeDao;
    }


    public List<Employee> getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        return employees;
    }

    public Employee getEmployee(int employeeId) {
        Employee employee = employeeDao.getEmployee(employeeId);
        return employee;
    }

    public int deleteEmployee(int employeeId) {
        return employeeDao.deleteEmployee(employeeId);
    }

    public int updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    public int createEmployee(Employee employee) {
        return employeeDao.createEmployee(employee);
    }
}
