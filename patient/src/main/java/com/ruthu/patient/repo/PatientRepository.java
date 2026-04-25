package com.ruthu.patient.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruthu.patient.entity.Patient;
import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

     Optional<Patient> findByEmail(String email);

     Optional<List<Patient>>  findByContactNumber(String contactNumber);

}
