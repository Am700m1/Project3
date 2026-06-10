package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.Model.Employee;
import org.example.project3.Model.User;
import org.example.project3.Repository.AuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final AuthRepository authRepository;

    public void register(User user){
        user.setRole("EMPLOYEE");
        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);

        if(user.getEmployee() != null){
            Employee employee = user.getEmployee();
            employee.setUser(user);
        }

        authRepository.save(user);
    }
}
