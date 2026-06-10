package org.example.project3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {


    @NotEmpty(message = "position must be included!")
    private String position;


    @NotNull(message = "Salary must not be null")
    @Positive(message = "Salary must be a positive number")
    private Integer salary;
}
