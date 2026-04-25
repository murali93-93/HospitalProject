package com.ruthu.opservice.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruthu.opservice.dto.DoctorDto;

@FeignClient(name = "Doctor", url = "http://localhost:8082")
public interface DoctorClient {

    @GetMapping("/doctorAPI/fetchDoctorsByProfessionType")
    List<DoctorDto> findByProfessionType(@RequestParam String professionType);

     
}
