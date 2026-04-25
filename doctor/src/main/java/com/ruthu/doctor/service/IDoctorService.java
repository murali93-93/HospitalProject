package com.ruthu.doctor.service;

import java.util.List;

import com.ruthu.doctor.dto.DoctorDto;
import com.ruthu.doctor.dto.DoctorResponseDto;


public interface IDoctorService {

    public void createDoctor(DoctorDto doctorDto);

    public DoctorResponseDto fetchDoctor(String doctorId);

    public List<DoctorResponseDto> fetchDoctorsByProfessionType(String professionType);

    public List<DoctorResponseDto> fetchAllDoctors();
}
