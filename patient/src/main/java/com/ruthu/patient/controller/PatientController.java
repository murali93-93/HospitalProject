package com.ruthu.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruthu.patient.dto.PatientContactInfoDto;
import com.ruthu.patient.dto.PatientDto;
import com.ruthu.patient.service.IPatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor // Injects PatientService via constructor
public class PatientController {

    private final IPatientService patientService;

    @Autowired
    private PatientContactInfoDto patientContactInfoDto;

     // CREATE: Add a new patient
    @PostMapping("/createPatient")
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientDto patientDto) {
        PatientDto savedPatient = patientService.savePatient(patientDto);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    // READ: Get all patients
    @GetMapping("/getAllPatients")
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // READ: Get a single patient by ID
    @GetMapping("/getPatientById/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    // UPDATE: Update existing patient details
    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<PatientDto> updatePatient(
            @PathVariable Long id, 
            @Valid @RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientDto));
    }

    // DELETE: Soft or hard delete a patient
    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully with ID: " + id);
    }

    // Additional endpoint to search patients by contact number
    @GetMapping("/getPatientsByContactNumber")
    public ResponseEntity<List<PatientDto>> getPatientsByContactNumber(@RequestParam String contactNumber) {
        return ResponseEntity.ok(patientService.getPatientsByContactNumber(contactNumber));
    }
        // Additional endpoint to save multiple patients at once
    @PostMapping("/savePatients")
    public ResponseEntity<String> savePatients(@Valid @RequestBody List<PatientDto> patientDtos) {
        patientService.savePatients(patientDtos);
        return ResponseEntity.ok("Patients saved successfully");
    }   

    @GetMapping("/patientContactInfoDetails")
    public ResponseEntity<PatientContactInfoDto> contactInfoDto(){
                 return ResponseEntity.status(HttpStatus.OK).body(patientContactInfoDto);
    }  
}
