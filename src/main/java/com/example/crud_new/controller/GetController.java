package com.example.crud_new.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.crud_new.service.GetService;


@RestController
@RequestMapping("/api")
public class GetController {
 
    @Autowired
    private GetService getService;

    @GetMapping("/getEmployee")
    public ResponseEntity<?> getEmployees(
            @RequestParam(required = false) Integer managerId,
            @RequestParam(required = false) Integer yearsOfExperience) {
        return getService.getEmployees(managerId, yearsOfExperience);
    }
}
