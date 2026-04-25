package com.ruthu.opservice.serviceImpl;


import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import com.ruthu.opservice.dto.DoctorDto;
import com.ruthu.opservice.dto.OPVisitDto;
import com.ruthu.opservice.dto.OPVisitResponseDto;
import com.ruthu.opservice.dto.PatientDto;
import com.ruthu.opservice.entity.OPVisit;
import com.ruthu.opservice.enumm.VisitStatus;
import com.ruthu.opservice.exception.CustomException;
import com.ruthu.opservice.feignClient.DoctorClient;
import com.ruthu.opservice.feignClient.PateintClient;
import com.ruthu.opservice.repo.OPVisitRepository;
import com.ruthu.opservice.service.IOPVisitService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Log4j2
public class OPVisitServiceImpl implements IOPVisitService {

    final PateintClient pateintClient;
    final DoctorClient doctorClient;
    final OPVisitRepository opVisitRepository;
    private final Executor executor;

    @Override
    public void createOPVisit1(OPVisitDto opVisitDto) {
        long start = System.currentTimeMillis();
        log.info("Entered into Service for createOPVisitt");
        log.info("calling Doctor Service for Doctor Data");
        CompletableFuture<List<DoctorDto>> doctorFuture =
                CompletableFuture.supplyAsync(() -> {
                            return doctorClient.findByProfessionType(opVisitDto.getDoctorProfessionType());
                        }, executor).orTimeout(30, TimeUnit.SECONDS)
                        .exceptionally(ex -> {
                            log.error("Doctor service failed: {}", ex.getMessage());
                           // return Collections.emptyList();
                            throw new CustomException("Doctor service unavailable",ex.getCause());
                        });

        log.info("calling Patient Service for patient details");
        CompletableFuture<List<PatientDto>> patientFuture =
                CompletableFuture.supplyAsync(() ->
                                        pateintClient.getPatientsByMobileNumber(opVisitDto.getPatientPhoneNumber()),
                                executor
                        ).orTimeout(30, TimeUnit.SECONDS)
                        .exceptionally(ex -> {
                            log.error("Patient service failed: {}", ex.getMessage());
                            throw new CustomException("Patient service unavailable",ex.getCause());
                        });

        CompletableFuture.allOf(doctorFuture, patientFuture).join();
        List<DoctorDto> doctors = doctorFuture.join();
        List<PatientDto> patients = patientFuture.join();

        log.info("Fetched doctors: {}", doctors);
        log.info("Fetched patients: {}", patients);

        DoctorDto selectedDoctor = slectedDoctorr(doctors, opVisitDto);
        PatientDto selectedPatient = slectedPatientt(patients, opVisitDto);

        // 3. Map and SAVE (The entity must be returned from mapping to be saved)
        OPVisit opVisit = mapToOPVisitEntity(opVisitDto, selectedDoctor, selectedPatient);

        //   3. GENERATE TOKEN HERE
        //  In a real app, you'd call a repository method to get the count of visits for "today"
        //  e.g., String token = "OP-" + (opVisitRepository.countByVisitDate(LocalDate.now()) + 1);
        int randomNum = (int) (Math.random() * 900) + 100;
        opVisit.setTokenNumber("OP-" + randomNum);

        opVisitRepository.save(opVisit); // Must pass the object here
        long end = System.currentTimeMillis();
        log.info("Total Time : "+(end-start)+" ms");
    }

    private static DoctorDto slectedDoctorr(List<DoctorDto> doctors, OPVisitDto opVisitDto) {
        log.info("fetching the Doctor with required experience: {}", opVisitDto.getExperienceYears());
        DoctorDto selectedDoctor = doctors.stream()
                .filter(d -> d.getExperienceYears() >= opVisitDto.getExperienceYears())
                .findFirst()
                .orElseThrow(() ->
                        new CustomException("No doctor found with required experience: "
                                + opVisitDto.getExperienceYears())
                );
        log.info("Return the doctor with required experience");
        return selectedDoctor;
    }

    private static PatientDto slectedPatientt(List<PatientDto> patients, OPVisitDto opVisitDto) {
        log.info("Fetching Patient");
        PatientDto selectedPatient = patients.stream()
                .filter(p -> p.getName().equalsIgnoreCase(opVisitDto.getPatientName()))
                .findFirst()
                .orElseThrow(() ->
                        new CustomException("No patient found with name: "
                                + opVisitDto.getPatientName() + " and phone: "
                                + opVisitDto.getPatientPhoneNumber())
                );
        return selectedPatient;
    }

    private OPVisit mapToOPVisitEntity(OPVisitDto dto, DoctorDto doctor, PatientDto patient) {
        OPVisit entity = new OPVisit();
        entity.setPatientId(patient.getId());
        entity.setPatientName(patient.getName());
        entity.setPatientPhoneNumber(dto.getPatientPhoneNumber());
        entity.setDoctorName(doctor.getDoctorName());
        entity.setDoctorProfessionType(doctor.getProfessionType());
        entity.setMedicalLicenceNumber(doctor.getMedicalLicenseNumber());
        entity.setReasonForVisit(dto.getReasonForVisit());

        // Note: visitTime and status are handled by @PrePersist in your Entity class
        return entity;
    }

    private OPVisitResponseDto mapToResponseDto(OPVisit entity) {
        OPVisitResponseDto resp = new OPVisitResponseDto();
        resp.setOpVisitId(entity.getOpId());
        resp.setTokenNumber(entity.getTokenNumber());
        resp.setPatientName(entity.getPatientName());
        resp.setPatientId(entity.getPatientId());
        resp.setPatientPhoneNumber(entity.getPatientPhoneNumber());
        resp.setDoctorProfessionType(entity.getDoctorProfessionType());
        resp.setDoctorName(entity.getDoctorName());
        resp.setDoctorProfessionType(entity.getDoctorProfessionType());
        resp.setPatientId(entity.getPatientId());
        resp.setVisitTime(entity.getVisitTime().toString());
        resp.setStatus(entity.getStatus().toString());
        return resp;
    }

    @Override
    public OPVisitResponseDto getOPVisitByOpId(Long opId) {

        OPVisit opVisit = opVisitRepository.findById(opId)
                .orElseThrow(() -> new CustomException("OP Visit not found with ID: " + opId));

        return mapToResponseDto(opVisit); // Pass null for patient as it's not needed here

    }

    // Implementations for startConsultation and completeConsultation would go here, updating the OPVisit status accordingly.
    @Override
    public OPVisitResponseDto startConsultation(Long opId) {
        OPVisit visit = opVisitRepository.findById(opId)
                .orElseThrow(() -> new CustomException("Not found"));

        visit.setStatus(VisitStatus.IN_CONSULTATION);
        opVisitRepository.save(visit);

        return mapToResponseDto(visit);
    }

    @Override
    public OPVisitResponseDto completeConsultation(Long opId) {
        OPVisit visit = opVisitRepository.findById(opId)
                .orElseThrow(() -> new CustomException("Not found"));

        visit.setStatus(VisitStatus.COMPLETED);
        opVisitRepository.save(visit);

        return mapToResponseDto(visit);
    }
}
