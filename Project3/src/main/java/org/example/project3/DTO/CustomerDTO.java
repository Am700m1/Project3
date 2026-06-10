package org.example.project3.DTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @NotEmpty(message = "Phone number must be filled!")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and be exactly 10 digits long")
    private String phoneNumber;

}
