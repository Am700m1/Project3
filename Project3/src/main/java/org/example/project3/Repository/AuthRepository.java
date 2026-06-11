package org.example.project3.Repository;

import org.example.project3.Model.Customer;
import org.example.project3.Model.Employee;
import org.example.project3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);

    User findUserById(Integer id);

    List<Customer> findUserByRole(String role);

    List<Employee> findUsersByRole(String role);
}
