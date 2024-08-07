package com.example.crud_new.model;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Employee {
    
    @Id
    private Integer id;
    @NotNull(message = "Name cannot be blank")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Designation cannot be blank")
    @Pattern(regexp = "^(account manager|associate)$", 
    message = "Designation can only be Account Manager or associate")
    private String designation;

    @NotBlank(message = "Department cannot be blank")
    @Pattern(regexp = "^(sales|delivery|QA|engineering|BA)$", message = "Invalid department")
    private String department;
    
    @Email(message="invalid email")
    private String email;

    @NotBlank(message = "Mobile cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
    private String mobile;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotNull(message = "Manager ID cannot be null")
    @Min(value = 0, message = "invalid")
    private Integer managerId;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dateOfJoining;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createdTime;
    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    // private LocalDateTime updatedTime;

    private Integer yearsOfExperience;
    
}
