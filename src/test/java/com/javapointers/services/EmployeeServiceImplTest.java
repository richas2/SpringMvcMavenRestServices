package com.javapointers.services;

import com.javapointers.bean.Employee;
import com.javapointers.bean.EmployeeVO;
import com.javapointers.dao.EmployeeDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeServiceImplTest {

    @Mock
    private static EmployeeDao mockedDao;

    private static EmployeeServiceImpl service;

    private static Employee emp1;
    private static Employee emp2;
    private static EmployeeVO emp3;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new EmployeeServiceImpl(mockedDao);

        emp1 = new Employee(1, "Rahul", "Sharma", 88);
        emp2 = new Employee(2, "Vikas", "Gupta", 98);
        emp3 = new EmployeeVO(3,"Vikas", "Gupta", 98);

    }

    @Test
    public void should_delete_employee() {
        when(mockedDao.deleteEmployee(1)).thenReturn(emp1.getEmployeeId());

        service.deleteEmployee(1);

        verify(mockedDao).deleteEmployee(1);
    }

    @Test
    public void should_get_all_employees() {
        when(mockedDao.getEmployees()).thenReturn(Arrays.asList(emp1, emp2));

        List<EmployeeVO> output = service.getEmployees();

        assertEquals(2, output.size());
    }

    @Test
    public void should_get_selected_employee() {
        when(mockedDao.getEmployee(1)).thenReturn(emp1);

        Employee myEmployee = mockedDao.getEmployee(1);
        EmployeeVO output = service.getEmployee(1);

        assertEquals(myEmployee.getFirstName(), output.getFirstName());
    }


    @Test
    public void should_update_employee() {
        when(mockedDao.updateEmployee(emp1)).thenReturn(emp1.getEmployeeId());

        int employeeId = mockedDao.updateEmployee(emp1);
        int output = service.updateEmployee(emp1);

        assertEquals(output, employeeId);
    }

    @Test
    public void should_create_employee() {
        when(mockedDao.createEmployee(emp1)).thenReturn(emp1.getEmployeeId());

        int output = mockedDao.createEmployee(emp1);
        int employeeId = service.createEmployee(emp1);

        assertEquals(output, employeeId);
    }
}