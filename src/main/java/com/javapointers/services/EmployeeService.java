package com.javapointers.services;


import com.javapointers.bean.Employee;
import com.javapointers.bean.EmployeeVO;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeVO> getEmployees();
    public EmployeeVO getEmployee(int employeeId);
    public int deleteEmployee(int employeeId);
    public int updateEmployee(Employee employee);
    public int createEmployee(Employee employee);
}