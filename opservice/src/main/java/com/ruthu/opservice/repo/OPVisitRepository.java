package com.ruthu.opservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruthu.opservice.entity.OPVisit;

@Repository
public interface OPVisitRepository extends JpaRepository<OPVisit, Long> {

}
