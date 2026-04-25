package com.ruthu.doctor.controller;

import java.util.List;

import com.ruthu.doctor.constants.DoctorApiConstants;
import com.ruthu.doctor.constants.DoctorMessages;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruthu.doctor.dto.DoctorContactInfoDto;
import com.ruthu.doctor.dto.DoctorDto;
import com.ruthu.doctor.dto.DoctorResponseDto;
import com.ruthu.doctor.dto.ResponseDto;
import com.ruthu.doctor.service.IDoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@RestController
@RequestMapping("/doctorAPI")
@Validated
@Log4j2
@Tag(name = "CURD Rest API For Doctor Service", description = "Curd Rest API For Doctor Service to CREATE, FETCH doctor information")
public class DoctorController {

  private final IDoctorService doctorService;

  public DoctorController(IDoctorService iDoctorService){
     this.doctorService=iDoctorService;
  }
  @Value("${build.version}")
  private String buildVersion;

  @Autowired
  private Environment environment;

  @Autowired
  private DoctorContactInfoDto doctorContactInfoDto;

    @PostMapping("/createDoctor")
    @Operation(summary = "Create Doctor", description = "API endpoint to create a new doctor with the provided details.")
    @ApiResponse(responseCode = "200", description = "HTTPS Status Created")
    public  ResponseEntity<ResponseDto> createDoctor(@Valid @RequestBody DoctorDto doctorDto){
       log.info("Entered into Controller class for Create Doctor");
         doctorService.createDoctor(doctorDto);
        log.info("Created successfully the Doctor");
         return ResponseEntity.ok
         (new ResponseDto(DoctorMessages.CREATED_SUCCESS, HttpStatus.CREATED));

    }
   @Operation(summary = "Fetch Doctor", description = "API endpoint to fetch doctor information by ID.")
   @ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTPS Status OK"),
                   @ApiResponse(responseCode = "404", description = "Doctor Not Found")  })
    @GetMapping("/fetchDoctor")
    public ResponseEntity<DoctorResponseDto> fetchDocotor(@RequestParam 
         @Pattern(regexp = "^[A-Za-z0-9]+$", message = "medicalLicenseNumber should be alphanumeric") String doctorId){
       log.info("Entered into Controller class for fetch Doctor");
          DoctorResponseDto doctorResponseDto = doctorService.fetchDoctor(doctorId);
       log.info("Successfully fetch the Doctor");
           return ResponseEntity.status(HttpStatus.OK).body(doctorResponseDto);
           
    }

     @GetMapping("/fetchDoctorsByProfessionType")
    public ResponseEntity<List<DoctorResponseDto>> fetchDoctorsByProfessionType(@RequestParam @Size(min = 3, max = 50, message = "professionType should be between 3 and 50 characters") String professionType){
         log.info("Entered into Controller class for Fetch Doctor By ProfessionType");
         List<DoctorResponseDto> doctorResponseDtos = doctorService.fetchDoctorsByProfessionType(professionType);
         log.info("Successfully fetch the Doctor By ProffessionType");
         return ResponseEntity.status(HttpStatus.OK).body(doctorResponseDtos);
    }

    @GetMapping("/getAllDoctors")
    public ResponseEntity<List<DoctorResponseDto>> fetchAllDoctors(){
        log.info("Entered into Controller class for fetch All Doctors");
        List<DoctorResponseDto> doctorResponseDtos = doctorService.fetchAllDoctors();
        log.info("Successfully fetched the All Doctors");
        return ResponseEntity.status(HttpStatus.OK).body(doctorResponseDtos);
    }

    @GetMapping("/versionInfo")
    public ResponseEntity<String> buildVersionInfo(){
                 return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
           
    }  

    @GetMapping("/envVersionInfo")
    public ResponseEntity<String> buildVersionnn(){
                 return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));    
    }  

    @GetMapping("/docotorContactInfoDetails")
    public ResponseEntity<DoctorContactInfoDto> contactInfoDto(){
                 return ResponseEntity.status(HttpStatus.OK).body(doctorContactInfoDto);
    }            
}
