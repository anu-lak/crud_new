package com.example.crud_new.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.crud_new.model.Employee;
import com.example.crud_new.model.Manager;
import com.example.crud_new.repository.EmployeeRepo;
import com.example.crud_new.repository.ManagerRepo;
import com.example.crud_new.response.ApiResponse;
import java.util.Optional;
 
@Service
public class PutService {
 
    @Autowired
    private EmployeeRepo employeeRepo;
 
    @Autowired
    private ManagerRepo managerRepo;
 
    public ResponseEntity<ApiResponse> changeEmployeeManager(Integer employeeId, Integer newManagerId) {
        // Fetch the employee
        Optional<Employee> employeeOptional = employeeRepo.findById(employeeId);
        if (!employeeOptional.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Employee with ID " + employeeId + " not found."));
        }
        Employee employee = employeeOptional.get();
       
        // Fetch the original manager
        Optional<Manager> originalManagerOptional = managerRepo.findById(employee.getManagerId());
        if (originalManagerOptional.isPresent())
        {
            Manager originalManager = originalManagerOptional.get();
            // Remove the employee from the original manager's list
            originalManager.getEmployeeList().removeIf(e -> e.getId().equals(employeeId));
            managerRepo.save(originalManager);
        }
       
        // Fetch the new manager
        Optional<Manager> newManagerOptional = managerRepo.findById(newManagerId);
        if (!newManagerOptional.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("New manager with ID " + newManagerId + " not found."));
        }
        Manager newManager = newManagerOptional.get();
       
        // Add the employee to the new manager's list
        newManager.getEmployeeList().add(employee);
        managerRepo.save(newManager);
       
        // Update the employee's manager ID
        employee.setManagerId(newManagerId);

       
        // Build the response
        String originalManagerName = originalManagerOptional.isPresent() ? originalManagerOptional.get().getName() : "Unknown";
        String newManagerName = newManager.getName();
       
        return ResponseEntity.ok(new ApiResponse(
                employee.getName() + "'s manager has been successfully changed from " + originalManagerName + " to " + newManagerName + "."
        ));
    }
}