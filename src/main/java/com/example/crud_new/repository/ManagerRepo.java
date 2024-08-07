package com.example.crud_new.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.crud_new.model.Manager;


@Repository
public interface ManagerRepo extends MongoRepository<Manager, Integer>{
    Optional<Manager> findById(String id);
    Optional<Manager> findByDepartment(String department);
    
}
