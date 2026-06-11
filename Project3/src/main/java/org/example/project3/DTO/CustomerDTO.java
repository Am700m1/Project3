package org.example.project3.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

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


    @NotEmpty(message = "Phone number must be filled!")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and be exactly 10 digits long")
    private String phoneNumber;

}
