package com.ruthu.opservice.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruthu.opservice.dto.PatientDto;


@FeignClient(name = "Patient", url = "http://localhost:8083")
public interface PateintClient {

    @GetMapping("/api/patients/getPatientsByContactNumber")
    List<PatientDto> getPatientsByMobileNumber(@RequestParam String contactNumber);

    @GetMapping("/api/patients/getPatientById/{id}")
    PatientDto getPatientById(@PathVariable("id") Long id);

     @PostMapping("/createPatient")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto);

}
