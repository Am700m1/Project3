package org.example.project3.Controller;

import lombok.RequiredArgsConstructor;
import org.example.project3.Api.ApiResponse;
import org.example.project3.Model.User;
import org.example.project3.Service.CustomerService;
import org.example.project3.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final CustomerService customerService;

    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        customerService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("User was registered successfully"));
    }

    @PostMapping("/register/employee")
    public ResponseEntity<?> registerEmployee(@RequestBody User user){
        employeeService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("Employee was registered successfully"));
    }
}
