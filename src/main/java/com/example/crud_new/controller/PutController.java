package com.example.crud_new.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.crud_new.Input_Class.ChangeManagerRequest;
import com.example.crud_new.response.ApiResponse;
import com.example.crud_new.service.PutService;

@RestController
@RequestMapping("/api")
public class PutController {

    @Autowired
    private PutService putService;
    
    @PutMapping("/changeEmployeeManager")
    public ResponseEntity<ApiResponse> changeEmployeeManager(
            @RequestBody ChangeManagerRequest request) {
        return putService.changeEmployeeManager(request.getEmployeeId(), request.getManagerId());
    }   
}
