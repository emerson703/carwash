package com.project.carWash.repository;

import com.project.carWash.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    // Buscar por placa
    Optional<Vehicle> findByPlaca(String placa);

    // Buscar vehículos por cliente
    List<Vehicle> findByCustomerIdCustomer(Long customerId);



}
