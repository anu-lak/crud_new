package com.example.crud_new.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud_new.response.ApiResponse;
import com.example.crud_new.service.DeleteService;

@RestController
@RequestMapping("/api")
public class DeleteController {

    @Autowired
    private DeleteService deleteService;
    
    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<ApiResponse> deleteEmployee(@RequestParam Integer employeeId) {
        return deleteService.deleteEmployee(employeeId);
    }
}
