package com.ruthu.opservice.dto;

import java.time.LocalDateTime;


import com.ruthu.opservice.enumm.VisitStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OPVisitDto {

       // The specific family member's ID
  
    @NotEmpty(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits")
    private String patientPhoneNumber; // The specific family member's contact number
    
    @NotEmpty(message = "Patient name is required")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String patientName;

    // private String doctorId;

    // private String DoctorName;  // Assigned Doctor's ID
     @NotEmpty(message = "professionType should not be empty")
    @Size(min = 3, max = 50, message = "professionType should be between 2 and 50 characters")
    private String doctorProfessionType;

     private Long experienceYears; // e.g., "Cardiologist", "Dermatologist"
     
    private String tokenNumber; // Generated e.g., "DOC-101"
    private String reasonForVisit;
    private LocalDateTime visitTime;

     @Enumerated(EnumType.STRING)
     private VisitStatus status; // WAITING, IN_CONSULTATION, COMPLETED 

}
