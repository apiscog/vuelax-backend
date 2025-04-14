package com.example.vuelax.vuelax.service;

import com.example.vuelax.vuelax.mapper.FlightOptionMapper;
import com.example.vuelax.vuelax.model.DTO.FlightOptionDTO;
import com.example.vuelax.vuelax.model.enitity.FlightOptionEntity;
import com.example.vuelax.vuelax.repository.FlightOptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightOptionService {
    private final FlightOptionRepository flightOptionRepositoryRepository;
    private final FlightOptionMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightOptionService.class);

    public FlightOptionService(FlightOptionRepository repository, FlightOptionMapper mapper) {
        this.flightOptionRepositoryRepository = repository;
        this.mapper = mapper;
    }

    public FlightOptionEntity  saveFlight(FlightOptionDTO dto) {
        return flightOptionRepositoryRepository.save(mapper.toEntity(dto));
    }

    public List<FlightOptionDTO> getAllFlights() {
        List<FlightOptionEntity> flights = flightOptionRepositoryRepository.findAll();
        LOGGER.info("Flights from repository: {}", flights);

        List<FlightOptionDTO> flightDTOs = flights.stream()
                .map(mapper::toDTO)
                .toList();

        LOGGER.info("Mapped FlightOptionDTOs: {}", flightDTOs);
        return flightDTOs;
    }
}
