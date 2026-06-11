package org.example.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.project3.Api.ApiResponse;
import org.example.project3.DTO.CustomerDTO;
import org.example.project3.Model.User;
import org.example.project3.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCustomer(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomer(user.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid CustomerDTO customerDTO){
        customerService.register(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer was registered successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@AuthenticationPrincipal User oldUser, @RequestBody @Valid CustomerDTO customerDTO){
        customerService.updateCustomer(oldUser.getId(), , customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer updated "));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@AuthenticationPrincipal User user){
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Customer deleted "));
    }
}
