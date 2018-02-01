package com.javapointers.controllers;

import com.javapointers.bean.Employee;
import com.javapointers.bean.EmployeeVO;
import com.javapointers.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
public class EmployeeControllerTest {


    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeServiceMock;

    private static EmployeeVO emp1;
    private static EmployeeVO emp2;
    private static Employee emp3;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        employeeController =new EmployeeController(employeeServiceMock);

        emp1 = new EmployeeVO( "Rahul", "Sharma", 88);
        emp2 = new EmployeeVO( "Vikas", "Gupta", 98);
        emp3 = new Employee( 3,"Vikas", "Gupta", 98);
    }

    @Test
    public void should_return_employees() throws Exception{
        when(employeeServiceMock.getEmployees()).thenReturn(Arrays.asList(emp1, emp2));

        String output=  new StringBuilder().append(employeeController.employees()).append("").toString();
        String expected="<200 OK,[EmployeeVO{firstName='Rahul', lastName='Sharma', age=88}, EmployeeVO{firstName='Vikas', lastName='Gupta', age=98}],{Number Of Records Found=[2]}>";

        assertEquals(output,expected);


    }

    @Test
    public void should_return_selected_employee() {
        when(employeeServiceMock.getEmployee(1)).thenReturn(emp1);

        String output=  new StringBuilder().append(employeeController.getEmployee((1))).append("").toString();
        String expected="<200 OK,EmployeeVO{firstName='Rahul', lastName='Sharma', age=88},{}>";

        assertEquals(output,expected);

    }

    @Test
    public void should_delete_employee() {
        when(employeeServiceMock.deleteEmployee(1)).thenReturn(1);
        when(employeeServiceMock.getEmployee(1)).thenReturn(emp1);

        String output=  new StringBuilder().append(employeeController.deleteEmployee((1))).append("").toString();
        String expected="<204 No Content,EmployeeVO{firstName='Rahul', lastName='Sharma', age=88},{Employee Deleted - =[1]}>";

        assertEquals(output,expected);
    }

    @Test
    public void should_create_employee() {
        doReturn(3).when(employeeServiceMock).createEmployee(emp3);

        String output=  new StringBuilder().append(employeeController.createEmployee((emp3))).append("").toString();
        String expected="<201 Created,Employee Id:- 3 First Name:- Vikas Last Name:- Gupta Age:- 98,{Employee Created  - =[3]}>";

        assertEquals(output,expected);
    }

    @Test
    public void should_update_employee() {
        when(employeeServiceMock.updateEmployee(emp3)).thenReturn(3);
        when(employeeServiceMock.getEmployee(1)).thenReturn(emp1);

        String output=  new StringBuilder().append(employeeController.updateEmployee(1,emp3)).append("").toString();
        String expected="<200 OK,Employee Id:- 3 First Name:- Vikas Last Name:- Gupta Age:- 98,{Employee Updated  - =[1]}>";

        assertEquals(output,expected);

    }
}