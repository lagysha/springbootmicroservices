package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@Tag(
        name = "Organization Controller",
        description = "Organization Controller Exposes REST APIs for Organization-Service"
)
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    @Operation(
            summary = "Save Organization REST API",
            description = "Save Organization REST API is used to save organization object in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    @Operation(
            summary = "Save Organization REST API",
            description = "Get Organization REST API is used to get organization object from a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("code") String organizationCode){
       OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
       return ResponseEntity.ok(organizationDto);
    }
}
