package com.ruthu.doctor.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DoctorDto {

    @NotEmpty(message = "doctorName should not be empty")
    @Size(min = 3, max = 50, message = "doctorName should be between 2 and 50 characters")
    private String doctorName;

    @NotEmpty(message = "doctorMobileNumber should not be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "doctorMobileNumber should be a valid 10-digit number")
    private String doctorMobileNumber;

        @NotEmpty(message = "medicalLicenseNumber should not be empty")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "medicalLicenseNumber should be alphanumeric")
    private String medicalLicenseNumber;

    @NotEmpty(message = "professionType should not be empty")
    @Size(min = 3, max = 50, message = "professionType should be between 2 and 50 characters")
    private String professionType;

    @NotEmpty(message = "doctorEmail should not be empty")
    @Email(message = "doctorEmail should be a valid email address")
    private String doctorEmail;

    @NotNull(message = "Experience is required")
    @Min(value = 0, message = "Experience cannot be negative")
    private Long experienceYears;

    @NotEmpty(message = "specialization should not be empty")
    @Size(min = 3, max = 50, message = "specialization should be between 2 and 50 characters")
    private String specialization;

    @NotEmpty(message = "qualification should not be empty")
    @Size(min = 3, max = 100, message = "qualification should be between 3 and 100 characters")
    private String qualification;
  

    @NotNull(message = "activeStatus should not be null")
    private Boolean activeStatus;

  
}
