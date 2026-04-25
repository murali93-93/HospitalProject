package com.ruthu.doctor.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "doctors")
public record DoctorContactInfoDto(String message,Map<String,String> contactDetails,List<String> oncallSuport) {

}
