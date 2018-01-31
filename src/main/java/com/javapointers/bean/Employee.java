package com.javapointers.bean;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component("employee")
public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private int age;

    @JsonCreator
    public Employee(@JsonProperty("employeeId") int employeeId, @JsonProperty("firstName") String firstName,
                    @JsonProperty("lastName") String lastName, @JsonProperty("age") int age) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Employee() {

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Employee Id:- " + getEmployeeId());
        str.append(" First Name:- " + getFirstName());
        str.append(" Last Name:- " + getLastName());
        str.append(" Age:- " + getAge());
        return str.toString();
    }

}