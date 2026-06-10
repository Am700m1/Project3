package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.Model.Customer;
import org.example.project3.Model.User;
import org.example.project3.Repository.AuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final AuthRepository authRepository;


    public void register(User user){
        user.setRole("CUSTOMER");
        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);

        if(user.getCustomer() != null){
            Customer customer = user.getCustomer();
            customer.setUser(user);
        }

        authRepository.save(user);
    }
}
