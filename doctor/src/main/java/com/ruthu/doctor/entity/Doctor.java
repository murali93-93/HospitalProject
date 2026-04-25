package com.ruthu.doctor.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor @ToString
public class Doctor {

   @Id
    private String medicalLicenseNumber;
    
    private String doctorName;
 
    private String doctorMobileNumber;
    private Long experienceYears;
    private String specialization;
    private String qualification;

    private String professionType;

    private String doctorEmail;
    
    private Boolean activeStatus; 
    
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }



}
