package com.example.crud_new.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.crud_new.model.Employee;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee,Integer> {

    List<Employee> findAllByYearsOfExperienceGreaterThanEqual(Integer yearsOfExperience);

    List<Employee> findAllByManagerId(Integer managerId);

    List<Employee> findAllByManagerIdAndYearsOfExperienceGreaterThanEqual(Integer managerId, Integer yearsOfExperience);

    List<Employee> findByManagerId(Integer employeeId);

    List<Employee> findByManagerIdAndYearsOfExperience(Integer managerId, Integer yearsOfExperience);

    List<Employee> findByYearsOfExperience(Integer yearsOfExperience);
    
}
