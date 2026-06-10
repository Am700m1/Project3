package org.example.project3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotEmpty(message = "Username must be included!")
    @Size(min = 4, max = 10, message = "Username must consist of at least 4 characters and does not exceed 10 characters!")
    private String username;

    @NotEmpty(message = "password must be included!")
    @Size(min = 6 , message = "password must consist of at least 6 characters!")
    private String password;


    @NotEmpty(message = "Name must be included!")
    @Size(min = 2, max = 20, message = "Name must consist of at least 2 characters and does not exceed 20 characters!")
    private String name;


    @NotEmpty(message = "Email must be included!")
    @Email(message = "must have a valid email format")
    private String email;
}
