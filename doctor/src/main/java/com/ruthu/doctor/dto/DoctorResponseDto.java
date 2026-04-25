package com.ruthu.doctor.dto;

import lombok.Data;

@Data
public class DoctorResponseDto {

    private String doctorName;
    private String doctorMobileNumber;
    private String medicalLicenseNumber;
    private String professionType;  
    private String doctorEmail;
    private Long experienceYears;
    private String specialization;
    private String qualification;
    private String createdAt;
    private boolean activeStatus;

}
