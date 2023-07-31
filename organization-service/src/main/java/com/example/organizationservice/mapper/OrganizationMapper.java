package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {

    OrganizationMapper instance = Mappers.getMapper(OrganizationMapper.class);

    Organization OrganizationDtoToOrganization(OrganizationDto organizationDto);

    OrganizationDto OrganizationToOrganizationDto(Organization organization);
}
