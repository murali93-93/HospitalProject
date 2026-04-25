package com.ruthu.patient.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "patient")
public record PatientContactInfoDto(String message,Map<String,String> contactDetails,List<String> oncallSuport) {

}
