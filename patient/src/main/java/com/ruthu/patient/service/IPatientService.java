package com.ruthu.patient.service;

import java.util.List;

import com.ruthu.patient.dto.PatientDto;

public interface IPatientService {

    PatientDto savePatient(PatientDto patientDto);
    List<PatientDto> getAllPatients();
    PatientDto getPatientById(Long id);
    PatientDto updatePatient(Long id, PatientDto patientDto);
    void deletePatient(Long id);
    List<PatientDto> getPatientsByContactNumber(String contactNumber);
    void savePatients(List<PatientDto> patientDtos);

    }
