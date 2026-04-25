package com.ruthu.opservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruthu.opservice.dto.OPServiceContatInfoDto;
import com.ruthu.opservice.dto.OPVisitDto;
import com.ruthu.opservice.dto.OPVisitResponseDto;
import com.ruthu.opservice.dto.ResponseDto;
import com.ruthu.opservice.service.IOPVisitService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/opservice")
@Log4j2
public class OPController {

    final IOPVisitService opVisitService;

    @Autowired
    private OPServiceContatInfoDto opServiceContatInfoDto;

    @PostMapping("/createOPVisit")  
    public ResponseEntity<ResponseDto> createOPVisit(@RequestBody @Valid OPVisitDto opVisitDto) {
       log.info("Entered into Controller to CreateOPVisit");
        opVisitService.createOPVisit1(opVisitDto);
        log.info("Completed Controller Created OPVisit Successfully");
         return ResponseEntity.ok(new ResponseDto("OP Visit created successfully", HttpStatus.CREATED));
    }   

    @GetMapping("/getOPVisitByOpId/{opId}")    
    public ResponseEntity<OPVisitResponseDto> getOPVisitByOpId(@PathVariable Long opId) {
       log.info("Entered into Controller get_OPVisitByOpId method");
        OPVisitResponseDto opVisitDto = opVisitService.getOPVisitByOpId(opId);
        log.info("Completed Controller get_OPVisitByOpId Successfully");
        return ResponseEntity.ok(opVisitDto);
    }

    @PutMapping("/start/{opId}")
   public ResponseEntity<ResponseDto> start(@PathVariable Long opId) {
        log.info("Entered into Controller to StartOPVisit");
    opVisitService.startConsultation(opId);
    log.info("Completed Controller StartOPVisit Successfully");
       return ResponseEntity.ok(new ResponseDto("OP Visit started successfully", HttpStatus.OK));

   }

    @PutMapping("/complete/{opId}")
    public ResponseEntity<ResponseDto> complete(@PathVariable Long opId) {
        log.info("Entered into Controller to CompleteOPVisit");
    opVisitService.completeConsultation(opId);
    log.info("Completed Controller CompleteOPVisit Successfully");
        return ResponseEntity.ok(new ResponseDto("OP Visit completed successfully", HttpStatus.OK));
     }

     @GetMapping("/opserviceContactInfoDetails")
    public ResponseEntity<OPServiceContatInfoDto> contactInfoDto(){
                 return ResponseEntity.status(HttpStatus.OK).body(opServiceContatInfoDto);
    }  
}

