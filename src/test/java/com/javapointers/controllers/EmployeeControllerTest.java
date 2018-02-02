package com.javapointers.controllers;

import com.javapointers.bean.Employee;
import com.javapointers.bean.EmployeeVO;
import com.javapointers.services.EmployeeServiceImpl;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeControllerTest {


    private EmployeeController employeeController;

    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    private MockMvc mockMvc;
    private static EmployeeVO emp1;
    private static EmployeeVO emp2;
    private static Employee emp3;
    private static String baseUrl = "http://localhost:8080/SpringMVC4";
    private RestTemplate restTemplate = new RestTemplate();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        employeeController=new EmployeeController(employeeServiceMock);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();


        emp1 = new EmployeeVO(1, "Rahul", "Sharma", 88);
        emp2 = new EmployeeVO( 2,"Vikas", "Gupta", 98);
        emp3 = new Employee( 1,"Rahul", "Sharma", 88);
    }

    @Test
    public void should_return_employees() throws Exception{
        when(employeeServiceMock.getEmployees()).thenReturn(Arrays.asList(emp1, emp2));

        this.mockMvc.perform(get("/employee"))
                .andExpect(status().isOk());

    }

    @Test
    public void should_return_selected_employee() throws Exception{
        when(employeeServiceMock.getEmployee(1)).thenReturn(emp1);

        this.mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void should_delete_employee() throws Exception{
        when(employeeServiceMock.deleteEmployee(1)).thenReturn(1);
        when(employeeServiceMock.getEmployee(1)).thenReturn(emp1);

        this.mockMvc.perform(delete("/employee/delete/{Number}",1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_create_employee() throws Exception{
        doReturn(3).when(employeeServiceMock).createEmployee(emp3);

        String output=  new StringBuilder().append(employeeController.createEmployee((emp3))).append("").toString();
        String expected="<201 Created,Employee Id:- 1 First Name:- Rahul Last Name:- Sharma Age:- 88,{Employee Created  - =[1]}>";

        assertEquals(output,expected);
//        this.mockMvc.perform(post("/employee/").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
    }

    @Test
    public void should_update_employee() throws Exception{
        when(employeeServiceMock.updateEmployee(emp3)).thenReturn(1);
        when(employeeServiceMock.getEmployee(1)).thenReturn(emp1);

        Employee inst = mock(Employee.class, withSettings().serializable());
        when(inst.getEmployeeId()).thenReturn(1);
        when(inst.getFirstName()).thenReturn("Richa");
        when(inst.getLastName()).thenReturn("Singh");
        when(inst.getAge()).thenReturn(1);
        JSONObject json = new JSONObject();
        json.put("employeeId", "student");
        json.put("firstName", "student");
        json.put("lastName", "student");
        json.put("age", "student");

        String output=  new StringBuilder().append(employeeController.updateEmployee(1,emp3)).append("").toString();
        String expected="<200 OK,Employee Id:- 1 First Name:- Rahul Last Name:- Sharma Age:- 88,{Employee Updated  - =[1]}>";

        assertEquals(output,expected);

//        this.mockMvc.perform(put("/employee/1",inst)
//                        .content(String.valueOf(json)).contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk());

    }
}