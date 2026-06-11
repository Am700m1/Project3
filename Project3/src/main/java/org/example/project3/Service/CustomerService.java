package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.Api.ApiException;
import org.example.project3.DTO.CustomerDTO;
import org.example.project3.Model.Customer;
import org.example.project3.Model.User;
import org.example.project3.Repository.AuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final AuthRepository authRepository;

    public List<Customer> getCustomers(){
        List<Customer> customers = authRepository.findUserByRole("CUSTOMER");

        if(customers.isEmpty()){
            throw new ApiException("No customers were found!");
        }

        return customers;
    }

    public Customer getCustomer(Integer userId){
        User user = authRepository.findUserById(userId);

        if(user == null || user.getCustomer() == null){
            throw new ApiException("Customer not found!");
        }

        return user.getCustomer();
    }

    public void register(CustomerDTO customerDTO){
        User user = new User();

        user.setUsername(customerDTO.getUsername());
        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());
        user.setRole("CUSTOMER");

        Customer customer = new Customer();
        customer.setPhoneNumber(customer.getPhoneNumber());
        customer.setUser(user);

        user.setCustomer(customer);
        authRepository.save(user);
    }

    public void updateCustomer(Integer userId, CustomerDTO customerDTO){
        User oldUser = authRepository.findUserById(userId);

        if(oldUser == null || oldUser.getCustomer() == null){
            throw new ApiException("Customer not found!");
        }

        oldUser.setUsername(customerDTO.getUsername());
        oldUser.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        oldUser.setName(customerDTO.getName());
        oldUser.setEmail(customerDTO.getEmail());
        oldUser.getCustomer().setPhoneNumber(customerDTO.getPhoneNumber());
        authRepository.save(oldUser);
    }

    public void deleteCustomer(Integer userId){
        User user = authRepository.findUserById(userId);

        if(user == null || user.getCustomer() == null){
            throw new ApiException("Customer not found!");
        }

        authRepository.delete(user);
    }
}
