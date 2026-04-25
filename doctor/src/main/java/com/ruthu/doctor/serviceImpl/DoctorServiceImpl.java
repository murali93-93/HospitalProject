package com.ruthu.doctor.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.ruthu.doctor.constants.DoctorMessages;
import com.ruthu.doctor.exception.CustomException;
import com.ruthu.doctor.exception.ResourceNotFoundException;
import com.ruthu.doctor.exception.UserAlreadyExistsException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruthu.doctor.dto.DoctorDto;
import com.ruthu.doctor.dto.DoctorResponseDto;
import com.ruthu.doctor.entity.Doctor;
import com.ruthu.doctor.mapper.DoctorMapper;
import com.ruthu.doctor.repo.DoctorRepository;
import com.ruthu.doctor.service.IDoctorService;


@Service
@Log4j2
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void createDoctor(DoctorDto doctorDto) {
          log.info("Entered into Service class for Create Doctor");
           Doctor doctor = DoctorMapper.mapToDoctor(doctorDto);
                    Optional<Doctor> existingDoctor = doctorRepository.findByMedicalLicenseNumber(doctor.getMedicalLicenseNumber());
                    if (existingDoctor.isPresent()) {
                        log.info("Doctor already exists in DB");
                        throw new UserAlreadyExistsException(DoctorMessages.ALREADY_EXISTS);
                    }
        doctorRepository.save(doctor);
    }

    @Override
    public DoctorResponseDto fetchDoctor(String medicalLicenseNumber) {
       log.info("Entered into Service class for Fetch Doctor");
        Doctor doctor = doctorRepository.findByMedicalLicenseNumber(medicalLicenseNumber)
        .orElseThrow(()->

                new ResourceNotFoundException(DoctorMessages.MEDICAL_LICENSE_NUMBER,
                        medicalLicenseNumber));
             
        return DoctorMapper.mapToDoctorResponseDto(doctor);

        }

    @Override
    public List<DoctorResponseDto> fetchDoctorsByProfessionType(String professionType) {
        log.info("Entered into Service class for Fetch Doctor by ProfessionType");
        List<Doctor> doctors = doctorRepository.findByProfessionType(professionType);
        if (doctors.isEmpty()) {
            log.info("No doctors found in DB By ProfessionType");
            throw new CustomException(DoctorMessages.DOCTOR_PROFFESION_NOTFOUND);
        }
        log.info("Returning doctors from service to controller");
        return doctors.stream()
                .map(DoctorMapper::mapToDoctorResponseDto)
                .toList();

    }

    @Override
    public List<DoctorResponseDto> fetchAllDoctors() {
       log.info("Entered into Service class for Fetch All Doctors");
       List<Doctor> doctors = doctorRepository.findAll();
        if (doctors.isEmpty()) {
            log.info("Fetched Doctors EmptyList");
            throw new CustomException(DoctorMessages.NO_DOCTORS_FOUND);
        }
        return doctors.stream().filter(Doctor::getActiveStatus)
                .map(DoctorMapper::mapToDoctorResponseDto)
                .toList();
    }
}

