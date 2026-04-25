package com.ruthu.patient.serviceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ruthu.patient.dto.PatientDto;
import com.ruthu.patient.entity.Patient;
import com.ruthu.patient.exception.CustomException;
import com.ruthu.patient.repo.PatientRepository;
import com.ruthu.patient.service.IPatientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService{

    private final PatientRepository patientRepository;

    @Override
    public PatientDto savePatient(PatientDto patientDto) {
        Patient patient = mapToEntity(patientDto);
        Patient savedPatient = patientRepository.save(patient);
        return mapToDto(savedPatient);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        return mapToDto(patient);
    }
    

    @Override
    public PatientDto updatePatient(Long id, PatientDto patientDto) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        // Update fields
        existingPatient.setName(patientDto.getName());
        existingPatient.setContactNumber(patientDto.getContactNumber());
        existingPatient.setEmail(patientDto.getEmail());
        existingPatient.setAddress(patientDto.getAddress());
        existingPatient.setBloodGroup(patientDto.getBloodGroup());
        existingPatient.setActiveStatus(patientDto.getActiveStatus());
        existingPatient.setDateOfBirth(LocalDate.parse(patientDto.getDateOfBirth()));
        existingPatient.setGender(patientDto.getGender());
            existingPatient.setAdharNumber(patientDto.getAdharNumber());

        Patient updatedPatient = patientRepository.save(existingPatient);
        return mapToDto(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        patientRepository.delete(patient);
    }

    // --- MAPPING METHODS ---

    private Patient mapToEntity(PatientDto dto) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setGender(dto.getGender());
        patient.setContactNumber(dto.getContactNumber());
        patient.setEmail(dto.getEmail());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setAddress(dto.getAddress());
        patient.setActiveStatus(dto.getActiveStatus());
        // Converts String "YYYY-MM-DD" to LocalDate
        patient.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        patient.setAdharNumber(dto.getAdharNumber());
        return patient;
    }

    private PatientDto mapToDto(Patient entity) {
        PatientDto dto = new PatientDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setGender(entity.getGender());
        dto.setContactNumber(entity.getContactNumber());
        dto.setEmail(entity.getEmail());
        dto.setBloodGroup(entity.getBloodGroup());
        dto.setAddress(entity.getAddress());
        dto.setActiveStatus(entity.getActiveStatus());
        dto.setDateOfBirth(entity.getDateOfBirth().toString());
        return dto;
    }

    @Override
    public List<PatientDto> getPatientsByContactNumber(String contactNumber) {
       
         Optional<List<Patient>> patientsOptional= patientRepository.findByContactNumber(contactNumber);
        if (patientsOptional.isPresent()) {
            return patientsOptional.get().stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }else
        {
           throw new CustomException("No patients found with contact number: " + contactNumber);
        }
        

    }

    @Override
    public void savePatients(List<PatientDto> patientDtos) {
               
        patientRepository.saveAll(patientDtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList()));
    }
    

}
