package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.Api.ApiException;
import org.example.project3.DTO.EmployeeDTO;
import org.example.project3.Model.Employee;
import org.example.project3.Model.User;
import org.example.project3.Repository.AuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final AuthRepository authRepository;

    public List<Employee> getEmployees(){
        List<Employee> employees = authRepository.findUsersByRole("EMPLOYEE");

        if (employees.isEmpty()) {
            throw new ApiException("No employees were found!");
        }
        return employees;
    }

    public Employee getEmployee(Integer userId){
        User user = authRepository.findUserById(userId);

        if(user == null || user.getEmployee() == null){
            throw new ApiException("Employee not found!");
        }

        return user.getEmployee();
    }

    public void register(EmployeeDTO employeeDTO){

        User user = new User();
        user.setUsername(employeeDTO.getUsername());
        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());
        user.setRole("EMPLOYEE");

        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employee.getSalary());
        employee.setUser(user);

        user.setEmployee(employee);
        authRepository.save(user);
    }

    public void updateEmployee(Integer userId, EmployeeDTO employeeDTO){
        User oldUser = authRepository.findUserById(userId);

        if(oldUser == null || oldUser.getEmployee() == null){
            throw new ApiException("Employee not found!");
        }


        oldUser.setUsername(employeeDTO.getUsername());
        oldUser.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
        oldUser.setName(employeeDTO.getName());
        oldUser.setEmail(employeeDTO.getEmail());
        oldUser.getEmployee().setPosition(employeeDTO.getPosition());
        oldUser.getEmployee().setSalary(employeeDTO.getSalary());
        authRepository.save(oldUser);
    }

    public void deleteEmployee(Integer userId){
        User user = authRepository.findUserById(userId);

        if(user == null || user.getEmployee() == null){
            throw new ApiException("Employee not found!");
        }

        authRepository.delete(user);
    }
}
