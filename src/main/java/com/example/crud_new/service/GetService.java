package com.example.crud_new.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.crud_new.model.Employee;
import com.example.crud_new.model.Manager;
import com.example.crud_new.repository.EmployeeRepo;
import com.example.crud_new.repository.ManagerRepo;
import com.example.crud_new.response.ManagerResponse;
 
@Service
public class GetService {
 
    @Autowired
    private EmployeeRepo employeeRepo;
 
    @Autowired
    private ManagerRepo managerRepo;
 
    public ResponseEntity<?> getEmployees(Integer managerId, Integer yearsOfExperience)
    {
        List<Employee> employees;
 
        if (managerId != null && yearsOfExperience != null)
        {
            employees = employeeRepo.findByManagerIdAndYearsOfExperience(managerId, yearsOfExperience);
        } else if (managerId != null)
        {
            employees = employeeRepo.findByManagerId(managerId);
        } else if (yearsOfExperience != null)
        {
            employees = employeeRepo.findByYearsOfExperience(yearsOfExperience);
        } else
        {
            employees = employeeRepo.findAll();
        }
 
        // Filter out employees with managerId of 0
        employees = employees.stream()
                .filter(emp -> emp.getManagerId() != 0)
                .collect(Collectors.toList());
 
        if (employees.isEmpty()) {
            return ResponseEntity.ok(new ManagerResponse("No employees found", null, null, null, List.of()));
        }
 
        // Group employees by manager ID
        List<ManagerResponse> responseList = employees.stream()
                .collect(Collectors.groupingBy(Employee::getManagerId))
                .entrySet().stream()
                .map(entry -> {
                    Integer empManagerId = entry.getKey();
                    List<Employee> employeeList = entry.getValue();
                    Manager manager = managerRepo.findById(empManagerId).orElse(null);
 
                    return new ManagerResponse(
                            "Successfully fetched",
                            manager != null ? manager.getName() : "Unknown",
                            manager != null ? manager.getDepartment() : "Unknown",
                            empManagerId,
                            employeeList
                    );
                })
                .collect(Collectors.toList());
 
        // Return the formatted response
        return ResponseEntity.ok(responseList);
    }

}
