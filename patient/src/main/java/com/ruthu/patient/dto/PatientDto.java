package com.ruthu.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientDto {

    private Long id;
    @NotEmpty(message = "Patient name is required")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @NotEmpty(message = "Date of birth is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in YYYY-MM-DD format")
    private String dateOfBirth;

    @NotEmpty(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
    private String gender;

    @NotEmpty(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits")
    private String contactNumber;

    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Blood group is required")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Invalid blood group (e.g., A+, O-)")
    private String bloodGroup;

    private String address;

    @NotNull(message = "Active status must be specified")
    private Boolean activeStatus; 

    @NotEmpty(message = "Adhar number is required")
    @Pattern(regexp = "^[0-9]{12}$", message = "Adhar number must be 12 digits")
    private String adharNumber;
}



