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
import java.util.Optional;

@Service
public class FlightOptionService {
    private final FlightOptionRepository flightOptionRepository;
    private final FlightOptionMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightOptionService.class);

    public FlightOptionService(FlightOptionRepository repository, FlightOptionMapper mapper) {
        this.flightOptionRepository = repository;
        this.mapper = mapper;
    }

    public FlightOptionEntity  saveFlight(FlightOptionDTO dto) {
        return flightOptionRepository.save(mapper.toEntity(dto));
    }

    public FlightOptionDTO getFlightOptionById(Long id) {
        Optional<FlightOptionEntity> flightOptionEntity = flightOptionRepository.findById(id);
        if (flightOptionEntity.isPresent()) {
            return mapper.toDTO(flightOptionEntity.get());
        } else {
            throw new RuntimeException("Flight option not found with id: " + id);
        }
    }

    public List<FlightOptionDTO> getAllFlights() {
        List<FlightOptionEntity> flights = flightOptionRepository.findAll();
        LOGGER.info("Flights from repository: {}", flights);

        List<FlightOptionDTO> flightDTOs = flights.stream()
                .map(mapper::toDTO)
                .toList();

        LOGGER.info("Mapped FlightOptionDTOs: {}", flightDTOs);
        return flightDTOs;
    }

    public FlightOptionDTO updateFlightOption(Long id, FlightOptionDTO flightOptionDTO) {
        Optional<FlightOptionEntity> existingFlightOption = flightOptionRepository.findById(id);

        if (existingFlightOption.isPresent()) {
            FlightOptionEntity flightOptionEntity = existingFlightOption.get();

            flightOptionEntity.setOrigin(flightOptionDTO.getOrigin());
            flightOptionEntity.setDestination(flightOptionDTO.getDestination());
            flightOptionEntity.setDepartureTime(flightOptionDTO.getDepartureTime());
            flightOptionEntity.setArrivalTime(flightOptionDTO.getArrivalTime());
            flightOptionEntity.setPrice(flightOptionDTO.getPrice());

            FlightOptionEntity updatedEntity = flightOptionRepository.save(flightOptionEntity);

            return mapper.toDTO(updatedEntity);
        } else {
            throw new RuntimeException("FlightOption with id " + id + " not found");
        }
    }

    public void deleteFlightOptionById(Long id) {
        if (flightOptionRepository.existsById(id)) {
            flightOptionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Flight option not found with id: " + id);
        }
    }
}
