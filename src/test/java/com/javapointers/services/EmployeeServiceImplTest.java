package com.javapointers.services;

import com.javapointers.bean.Employee;
import com.javapointers.services.EmployeeService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class EmployeeServiceImplTest {

    private static EmployeeService mockedEmpService;
    private static Employee emp1;
    private static Employee emp2;


    @BeforeClass
    public static void  setUp() {
        mockedEmpService = mock(EmployeeService.class);
        emp1 = new Employee(1, "Rahul", "Sharma", 88);
        emp2 = new Employee(2, "Vikas", "Gupta", 98);

        when(mockedEmpService.getEmployees()).thenReturn(Arrays.asList(emp1, emp2));
        when(mockedEmpService.getEmployee(1)).thenReturn(emp1);
        when(mockedEmpService.createEmployee(emp1)).thenReturn(emp1.getEmployeeId());
        when(mockedEmpService.updateEmployee(emp1)).thenReturn(emp1.getEmployeeId());
        when(mockedEmpService.deleteEmployee(1)).thenReturn(emp1.getEmployeeId());
    }

    @Test
    public void testGetEmployees() {
        List<Employee> allEmployees = mockedEmpService.getEmployees();
        assertEquals(2, allEmployees.size());
        Employee myEmployee = allEmployees.get(0);
        assertEquals("Rahul", myEmployee.getFirstName());
        assertEquals("Sharma", myEmployee.getLastName());
        assertEquals(1, myEmployee.getEmployeeId());
        assertEquals(88, myEmployee.getAge());

    }

    @Test
    public void testGetEmployee() {

        int employeeId=1;

        Employee myEmployee = mockedEmpService.getEmployee(1);

        assertNotNull(myEmployee);
        assertEquals(employeeId, myEmployee.getEmployeeId());

    }

    @Test
    public void testDeleteEmployee() {
        int employeeId = mockedEmpService.deleteEmployee(1);
        assertNotNull(employeeId);
        assertEquals(emp1.getEmployeeId(), employeeId);
    }

    @Test
    public void testUpdateEmployee() {
        int employeeId = mockedEmpService.updateEmployee(emp1);
        assertNotNull(employeeId);
        assertEquals(emp1.getEmployeeId(), employeeId);
    }

    @Test
    public void testCreateEmployee() {
        int employeeId = mockedEmpService.createEmployee(emp1);
        assertNotNull(employeeId);
        assertEquals(emp1.getEmployeeId(), employeeId);
    }
}