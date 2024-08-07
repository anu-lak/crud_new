package com.example.crud_new.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.crud_new.model.Employee;
import com.example.crud_new.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

    
    @Autowired
    private PostService postService;

        @PostMapping("/postEmployee")
        public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee) {
        
            return postService.createEmployee(employee);
    }

}
