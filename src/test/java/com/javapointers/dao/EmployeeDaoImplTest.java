package com.javapointers.dao;

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

public class EmployeeDaoImplTest {

    private static EmployeeDao mockedEmpDao;
    private static Employee emp1;
    private static Employee emp2;


    @BeforeClass
    public static void setUp() {
        mockedEmpDao = mock(EmployeeDao.class);
        emp1 = new Employee(111, "Rahul", "Sharma", 88);
        emp2 = new Employee(2, "Vikas", "Gupta", 98);

        when(mockedEmpDao.getEmployees()).thenReturn(Arrays.asList(emp1, emp2));
        when(mockedEmpDao.getEmployee(111)).thenReturn(emp1);
        when(mockedEmpDao.createEmployee(emp1)).thenReturn(emp2.getEmployeeId());
        when(mockedEmpDao.updateEmployee(emp1)).thenReturn(emp1.getEmployeeId());
        when(mockedEmpDao.deleteEmployee(111)).thenReturn(emp1.getEmployeeId());
    }

    @Test
    public void testGetEmployees() {
        List<Employee> allEmployees = mockedEmpDao.getEmployees();
        Employee myEmployee = allEmployees.get(0);
        assertEmployeeData(allEmployees, myEmployee);

    }

    @Test
    public void testGetEmployee() {

        int employeeId = 111;
        Employee myEmployee = mockedEmpDao.getEmployee(111);
        assertNotNull(myEmployee);
        assertEquals(employeeId, myEmployee.getEmployeeId());

    }

    @Test
    public void testDeleteEmployee() {
        int employeeId = mockedEmpDao.deleteEmployee(111);
        assertNotNull(employeeId);
        assertEquals(emp1.getEmployeeId(), employeeId);
    }

    @Test
    public void testUpdateEmployee() {
        int employeeId = mockedEmpDao.updateEmployee(emp1);
        assertNotNull(employeeId);
        assertEquals(emp1.getEmployeeId(), employeeId);
    }

    @Test
    public void testCreateEmployee() {
        int employeeId = mockedEmpDao.createEmployee(emp1);
        assertNotNull(employeeId);
        assertEquals(emp1.getEmployeeId(), employeeId);
    }

    public void assertEmployeeData(List<Employee> allEmployees, Employee myEmployee) {
        assertEquals(2, allEmployees.size());
        assertEquals("Rahul", myEmployee.getFirstName());
        assertEquals("Sharma", myEmployee.getLastName());
        assertEquals(111, myEmployee.getEmployeeId());
        assertEquals(88, myEmployee.getAge());

    }
}