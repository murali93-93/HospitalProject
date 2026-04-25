package com.ruthu.opservice.dto;

import lombok.Data;

@Data
public class DoctorDto {

  
    private String doctorName;
    private String doctorMobileNumber;
    private String professionType;  
    private String medicalLicenseNumber;
    
    private Long experienceYears;
    private String specialization;
    private String qualification;
   
  
}
