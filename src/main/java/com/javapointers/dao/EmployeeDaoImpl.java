package com.javapointers.dao;

import java.util.ArrayList;
import  java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.javapointers.bean.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

    List<Employee> employees =new ArrayList<>();

    @Autowired
    public EmployeeDaoImpl(List<Employee> employee) {
        this.employees = employee;
    }

    public List<Employee> getEmployees() {


        return employees;
    }

    public Employee getEmployee(int employeeId) {

        Employee employee = null;
        Employee emp = new Employee();;
        for(int i=0;i<employees.size();i++)
        {

            employee=employees.get(i);
            if(employee.getEmployeeId()==employeeId)
            {
                emp=employee;
                break;
            }

        }
        return emp;

    }

    public int deleteEmployee(int employeeId) {
        int count=0;
        Employee employeeLocal = null;
        for(int i=0;i<employees.size();i++)
        {

            employeeLocal=employees.get(i);
            if(employeeLocal.getEmployeeId()==employeeId)
            {
                employees.remove(employeeLocal);
                count=employeeLocal.getEmployeeId();
                break;
            }

        }
        return count;
    }

    public int updateEmployee(Employee employee) {
        int count=0;
        Employee employeeLocal = null;
        for(int i=0;i<employees.size();i++)
        {

            employeeLocal=employees.get(i);
            if(employeeLocal.getEmployeeId()==employee.getEmployeeId())
            {
                employees.remove(employeeLocal);
                employees.add(employee);
                count=employee.getEmployeeId();
                break;
            }

        }
        return count;
    }

    public int createEmployee(Employee employee) {

        employees.add(employee);
        return employee.getEmployeeId();


    }
}
