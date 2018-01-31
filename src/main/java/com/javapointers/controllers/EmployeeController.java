package com.javapointers.controllers;

import com.javapointers.bean.Employee;
import com.javapointers.bean.EmployeeVO;
import com.javapointers.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<EmployeeVO>> employees() {

        HttpHeaders headers = new HttpHeaders();
        List<EmployeeVO> employees = employeeService.getEmployees();

        if (employees == null) {
            return new ResponseEntity<List<EmployeeVO>>(HttpStatus.NOT_FOUND);
        }
        headers.add("Number Of Records Found", String.valueOf(employees.size()));
        return new ResponseEntity<List<EmployeeVO>>(employees, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeVO> getEmployee(@PathVariable("id") int employeeId) {
        EmployeeVO employee = employeeService.getEmployee(employeeId);
        if (employee == null) {
            return new ResponseEntity<EmployeeVO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<EmployeeVO> deleteEmployee(@PathVariable("id") int employeeId) {
        HttpHeaders headers = new HttpHeaders();
        EmployeeVO employee = employeeService.getEmployee(employeeId);
        if (employee == null) {
            return new ResponseEntity<EmployeeVO>(HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployee(employeeId);
        headers.add("Employee Deleted - ", String.valueOf(employeeId));
        return new ResponseEntity<EmployeeVO>(employee, headers, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        if (employee == null) {
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }
        employeeService.createEmployee(employee);
        headers.add("Employee Created  - ", String.valueOf(employee.getEmployeeId()));
        return new ResponseEntity<Employee>(employee, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int employeeId, @RequestBody Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        EmployeeVO isExist = employeeService.getEmployee(employeeId);
        if (isExist == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        } else if (employee == null) {
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }
        employeeService.updateEmployee(employee);
        headers.add("Employee Updated  - ", String.valueOf(employeeId));
        return new ResponseEntity<Employee>(employee, headers, HttpStatus.OK);
    }

}