package com.example.crud_new.response;

import java.util.List;

import com.example.crud_new.model.Employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManagerResponse {

    private String message;
    private String managerName;
    private String managerDepartment;
    private Integer managerId;
    private List<Employee> employeeList;
    public ManagerResponse(String message, String managerName, String managerDepartment, Integer managerId,
            List<Employee> employeeList) {
        this.message = message;
        this.managerName = managerName;
        this.managerDepartment = managerDepartment;
        this.managerId = managerId;
        this.employeeList = employeeList;
    }
   
}
