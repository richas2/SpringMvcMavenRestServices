package com.javapointers.services;

import com.javapointers.bean.Employee;
import com.javapointers.bean.EmployeeVO;
import com.javapointers.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao=employeeDao;
    }


    public List<EmployeeVO> getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        List<EmployeeVO> employeesVOList=new ArrayList<EmployeeVO>();
        EmployeeVO employeeVO=null;
        Employee employee=null;
        for(int i=0;i<employees.size();i++)
        {
            employee=employees.get(i);
            employeeVO=new EmployeeVO();
            employeeVO.setFirstName(employee.getFirstName());
            employeeVO.setLastName(employee.getLastName());
            employeeVO.setAge(employee.getAge());
            employeesVOList.add(employeeVO);
        }
        return employeesVOList;
    }

    public EmployeeVO getEmployee(int employeeId) {
        Employee employee = employeeDao.getEmployee(employeeId);
        EmployeeVO employeeVO=new EmployeeVO();
        employeeVO.setFirstName(employee.getFirstName());
        employeeVO.setLastName(employee.getLastName());
        employeeVO.setAge(employee.getAge());
        return employeeVO;
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
