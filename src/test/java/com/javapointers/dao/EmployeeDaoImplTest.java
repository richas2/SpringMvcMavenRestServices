package com.javapointers.dao;

import com.javapointers.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class EmployeeDaoImplTest {

    EmployeeDaoImpl dao;

    private static Employee emp1;
    private static Employee emp2;

    @Mock
    private ArrayList<Employee> mockEmployeesList;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        dao = new EmployeeDaoImpl(mockEmployeesList);
        emp1 = new Employee(1, "Rahul", "Sharma", 88);
        emp2 = new Employee(2, "Vikas", "Gupta", 98);

        doReturn(true).when(mockEmployeesList).remove(emp1);
        when(mockEmployeesList.get(0)).thenReturn(emp1);
        when(mockEmployeesList.size()).thenReturn(2);
        doReturn(true).when(mockEmployeesList).add(emp1);
    }

    @Test
    public void should_get_selected_employee() {

        Employee myEmployee = mockEmployeesList.get(0);
        Employee output = dao.getEmployee(1);

        assertEquals(myEmployee.getFirstName(), output.getFirstName());

    }

    @Test
    public void should_get_all_employees() {
        ArrayList<Employee> output = (ArrayList<Employee>) dao.getEmployees();

        assertEquals(mockEmployeesList, output);
    }

    @Test
    public void should_create_employee() {
        int output = dao.createEmployee(emp1);

        assertEquals(emp1.getEmployeeId(), output);
    }

    @Test
    public void should_update_employee() {
        int output = dao.updateEmployee(emp1);

        assertEquals(emp1.getEmployeeId(), output);
    }

    @Test
    public void should_delete_employee() {
        int output = dao.deleteEmployee(1);

        assertEquals(emp1.getEmployeeId(), output);
    }
}