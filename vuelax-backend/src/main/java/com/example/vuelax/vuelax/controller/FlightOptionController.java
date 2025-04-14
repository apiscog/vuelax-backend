package com.example.vuelax.vuelax.controller;

import com.example.vuelax.vuelax.model.DTO.FlightOptionDTO;
import com.example.vuelax.vuelax.model.enitity.FlightOptionEntity;
import com.example.vuelax.vuelax.service.FlightOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightOptionController {

    private final FlightOptionService flightOptionService;

    public FlightOptionController(FlightOptionService flightOptionService) {
        this.flightOptionService = flightOptionService;
    }

    @PostMapping
    public ResponseEntity<FlightOptionEntity> createFlight(@RequestBody FlightOptionDTO dto) {
        FlightOptionEntity saved = flightOptionService.saveFlight(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FlightOptionDTO>> getAllFlights() {
        List<FlightOptionDTO> flights = flightOptionService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightOptionDTO> getFlightOptionById(@PathVariable Long id) {
        FlightOptionDTO flightOptionDTO = flightOptionService.getFlightOptionById(id);
        return ResponseEntity.ok(flightOptionDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FlightOptionDTO> updateFlightOption(
            @PathVariable Long id,
            @RequestBody FlightOptionDTO flightOptionDTO) {

        try {
            FlightOptionDTO updatedFlightOption = flightOptionService.updateFlightOption(id, flightOptionDTO);
            return new ResponseEntity<>(updatedFlightOption, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlightOptionById(@PathVariable Long id) {
        flightOptionService.deleteFlightOptionById(id);
        return ResponseEntity.noContent().build();
    }
}
