package com.ruthu.doctor.mapper;

import com.ruthu.doctor.dto.DoctorDto;
import com.ruthu.doctor.dto.DoctorResponseDto;
import com.ruthu.doctor.entity.Doctor;


public class DoctorMapper {

    public static Doctor mapToDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setDoctorName(doctorDto.getDoctorName());
        doctor.setProfessionType(doctorDto.getProfessionType());
        doctor.setDoctorMobileNumber(doctorDto.getDoctorMobileNumber());
        doctor.setDoctorEmail(doctorDto.getDoctorEmail());
               doctor.setMedicalLicenseNumber(doctorDto.getMedicalLicenseNumber());
        doctor.setExperienceYears(doctorDto.getExperienceYears());
        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setQualification(doctorDto.getQualification());
        doctor.setActiveStatus(doctorDto.getActiveStatus());
        
        return doctor;
    }

    public static DoctorDto mapDoctorDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setDoctorName(doctor.getDoctorName());
        doctorDto.setProfessionType(doctor.getProfessionType());
        doctorDto.setDoctorMobileNumber(doctor.getDoctorMobileNumber());
        doctorDto.setDoctorEmail(doctor.getDoctorEmail());
        doctorDto.setMedicalLicenseNumber(doctor.getMedicalLicenseNumber());
        doctorDto.setExperienceYears(doctor.getExperienceYears());
        doctorDto.setSpecialization(doctor.getSpecialization());
        doctorDto.setQualification(doctor.getQualification());
        doctorDto.setActiveStatus(doctor.getActiveStatus());

        return doctorDto;
    }

    public static DoctorResponseDto mapToDoctorResponseDto(Doctor doctor) {
        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        doctorResponseDto.setDoctorName(doctor.getDoctorName());
        doctorResponseDto.setProfessionType(doctor.getProfessionType());
        doctorResponseDto.setDoctorMobileNumber(doctor.getDoctorMobileNumber());
        doctorResponseDto.setDoctorEmail(doctor.getDoctorEmail());
        doctorResponseDto.setMedicalLicenseNumber(doctor.getMedicalLicenseNumber());
        doctorResponseDto.setExperienceYears(doctor.getExperienceYears());
        doctorResponseDto.setSpecialization(doctor.getSpecialization());
        doctorResponseDto.setQualification(doctor.getQualification());
        doctorResponseDto.setActiveStatus(doctor.getActiveStatus());
            doctorResponseDto.setCreatedAt(doctor.getCreatedAt().toString());

        return doctorResponseDto;
    }
}
