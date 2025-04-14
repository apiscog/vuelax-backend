package com.example.vuelax.vuelax.repository;


import com.example.vuelax.vuelax.model.enitity.FlightOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightOptionRepository extends JpaRepository<FlightOptionEntity, Long> {
}