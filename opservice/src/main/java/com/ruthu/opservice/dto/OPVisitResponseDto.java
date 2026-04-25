package com.ruthu.opservice.dto;

import lombok.Data;

@Data
public class OPVisitResponseDto {

    private Long opVisitId;
    private String tokenNumber;
    private long patientId;
    private String patientName;
    private String patientPhoneNumber;
    private String doctorProfessionType;    
    private String doctorName;

    private String visitTime;
    private String status;

}
