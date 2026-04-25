package com.ruthu.opservice.dto;

import lombok.Data;

@Data
public class PatientDto {

    private Long id;
    private String name;
    private String dateOfBirth;
    private String contactNumber;
    private String aadharNumber;
    private String patientName;
    private String gender;
      private String email;
    private String bloodGroup;
    private String address;
    private Boolean activeStatus; 
}
