package com.example.crud_new.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.crud_new.model.Employee;
import com.example.crud_new.repository.EmployeeRepo;
import com.example.crud_new.response.ApiResponse;

import java.util.List;
import java.util.Optional;
 
@Service
public class DeleteService {
 
    @Autowired
    private EmployeeRepo employeeRepo;
 
    public ResponseEntity<ApiResponse> deleteEmployee(Integer employeeId)
    {
        // Check if the employee exists
        Optional<Employee> employeeOptional = employeeRepo.findById(employeeId);
 
        if (employeeOptional.isPresent())
        {
            Employee employee = employeeOptional.get();
 
            // Check if the employee has any subordinates
            List<Employee> subordinates = employeeRepo.findByManagerId(employeeId);
 
            if (subordinates.isEmpty())
            {
                // Delete the employee and return message
                employeeRepo.delete(employee);
                return ResponseEntity.ok(new ApiResponse("Successfully deleted " + employee.getName() + " from employee list of the organization"));
            }
            else
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse("Cannot delete employee with ID " + employeeId + " as they have subordinates."));
            }
        }
        else
        {
            // if employee does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Employee with ID " + employeeId + " not found."));
        }
    }
}