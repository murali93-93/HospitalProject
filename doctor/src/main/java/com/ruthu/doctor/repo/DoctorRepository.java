package com.ruthu.doctor.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruthu.doctor.entity.Doctor;
import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    Optional<Doctor> findByMedicalLicenseNumber(String medicalLicenseNumber);

    List<Doctor> findByProfessionType(String professionType);

}
