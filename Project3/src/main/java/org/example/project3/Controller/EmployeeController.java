package org.example.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.project3.Api.ApiResponse;
import org.example.project3.DTO.EmployeeDTO;
import org.example.project3.Model.User;
import org.example.project3.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployees());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getEmployee(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployee(user.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.register(employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee was registered successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@AuthenticationPrincipal User oldUser, @RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.updateEmployee(oldUser.getId(), employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee updated "));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployee(@AuthenticationPrincipal User user){
        employeeService.deleteEmployee(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee deleted "));
    }
}
