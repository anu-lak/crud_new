package com.example.crud_new.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Manager {

    // @Autowired
    // private ManagerRepo managerRepo;

    private String name;
    private String department;
    private Integer id;
    private List<Employee> employeeList = new ArrayList<>();;

    public void addEmployee(Employee employee) {
        this.employeeList.add(employee);
    }

    public Manager() {
    }


}
