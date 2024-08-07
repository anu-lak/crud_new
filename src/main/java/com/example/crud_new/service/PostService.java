package com.example.crud_new.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.crud_new.model.Employee;
import com.example.crud_new.model.Manager;
import com.example.crud_new.repository.EmployeeRepo;
import com.example.crud_new.repository.ManagerRepo;
import com.example.crud_new.response.ApiResponse;

@Service
public class PostService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ManagerRepo managerRepo;
    public ResponseEntity<?> createEmployee(Employee employee) {

    if (employee.getDesignation().equals("account manager") && employee.getManagerId() != 0) {
        return ResponseEntity.badRequest().body(new ApiResponse("Invalid request: Manager cannot have a manager ID"));
    }
    

     // Check if department already has a manager
     if (employee.getDesignation().equals("account manager")) {
        Optional<Manager> existingManager = managerRepo.findByDepartment(employee.getDepartment());
        if (existingManager.isPresent()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("A manager already exists in the department: " + employee.getDepartment()));
        }
    }

    List<Employee> allEmployees = employeeRepo.findAll();
    Integer maxId = allEmployees.stream()
                                .mapToInt(Employee::getId)
                                .max()
                                .orElse(0);  // Default to 0 if no employees exist
    employee.setId(maxId + 1);

    employee.setCreatedTime(LocalDateTime.now());
    calculateYearsOfExperience(employee);

    employeeRepo.save(employee);


    // Add employee to ManagerRepo if manager
    if (employee.getDesignation().equals("account manager")) {
        Manager manager = new Manager( employee.getName(), employee.getDepartment(),employee.getId(), new ArrayList<>());
        managerRepo.save(manager);
        return ResponseEntity.ok(new ApiResponse("Employee added as Manager successfully with ID: " + employee.getId()));
    }

    // Regular employee addition
    Optional<Manager> managerOptional = managerRepo.findById(employee.getManagerId());
    if (managerOptional.isPresent()) {
        Manager manager = managerOptional.get();
        manager.addEmployee(employee);
        managerRepo.save(manager);
    }
    ApiResponse response = new ApiResponse("Successfully created");
    return ResponseEntity.ok(response);
    }   

    private void calculateYearsOfExperience(Employee employee) {
        if (employee.getDateOfJoining() != null) {
            LocalDate joiningDate = employee.getDateOfJoining().toLocalDate();
            LocalDate currentDate = LocalDate.now();
            employee.setYearsOfExperience(Period.between(joiningDate, currentDate).getYears());
        }
    }
}
