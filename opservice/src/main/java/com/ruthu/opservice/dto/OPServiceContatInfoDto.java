package com.ruthu.opservice.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "opservice")
public record OPServiceContatInfoDto(String message,Map<String,String> contactDetails,List<String> oncallSuport) {



}
