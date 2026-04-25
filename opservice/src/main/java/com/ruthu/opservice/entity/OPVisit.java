package com.ruthu.opservice.entity;

import java.time.LocalDateTime;

import com.ruthu.opservice.enumm.VisitStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "op_visits")
@Data
public class OPVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opId;

    private Long patientId; // The specific family member's ID
    private String patientName; // The specific family member's name
    private String patientPhoneNumber; // The specific family member's contact number
    private String medicalLicenceNumber; // Assigned Doctor's ID
    private String DoctorName;  // Assigned Doctor's ID
      private String doctorProfessionType;
     
    @Column(unique = true)
    private String tokenNumber; 
    
    private String reasonForVisit;
    private LocalDateTime visitTime;


    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    // This logic runs automatically before saving to DB
    @PrePersist
    public void generateToken() {
        this.visitTime = LocalDateTime.now();
        this.status = VisitStatus.WAITING;
        
        // We use a simple timestamp-based or random suffix for uniqueness 
        // in a real app, you'd query the DB for the "count of the day"
        int randomNum = (int) (Math.random() * 900) + 100; 
        this.tokenNumber = "OP-" + randomNum; 
    }

}
