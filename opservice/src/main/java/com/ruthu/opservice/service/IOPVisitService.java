package com.ruthu.opservice.service;

import com.ruthu.opservice.dto.OPVisitDto;
import com.ruthu.opservice.dto.OPVisitResponseDto;

public interface IOPVisitService {

       //void createOPVisit(OPVisitDto opVisitDto);
       void createOPVisit1(OPVisitDto opVisitDto);


        OPVisitResponseDto getOPVisitByOpId(Long id);

        OPVisitResponseDto startConsultation(Long opId);

        OPVisitResponseDto completeConsultation(Long opId);
        
        // void getOPVisitsByPatientId();
        // void getOPVisitsByDoctorId();
        // void updateOPVisit();
        //void deleteOPVisit();

}
