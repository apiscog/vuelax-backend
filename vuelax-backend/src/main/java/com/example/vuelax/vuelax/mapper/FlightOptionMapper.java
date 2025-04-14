package com.example.vuelax.vuelax.mapper;

import com.example.vuelax.vuelax.constants.Constants;

import com.example.vuelax.vuelax.model.DTO.FlightOptionDTO;
import com.example.vuelax.vuelax.model.enitity.FlightOptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = Constants.SPRING)
public interface FlightOptionMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "origin", target = "origin")
    @Mapping(source = "destination", target = "destination")
    @Mapping(source = "departureTime", target = "departureTime")
    @Mapping(source = "arrivalTime", target = "arrivalTime")
    @Mapping(source = "price", target = "price")
    FlightOptionDTO toDTO(FlightOptionEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "origin", target = "origin")
    @Mapping(source = "destination", target = "destination")
    @Mapping(source = "departureTime", target = "departureTime")
    @Mapping(source = "arrivalTime", target = "arrivalTime")
    @Mapping(source = "price", target = "price")
    FlightOptionEntity toEntity(FlightOptionDTO dto);
}
