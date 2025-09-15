package com.project.carWash.controllers;

import com.project.carWash.entity.Vehicle;
import com.project.carWash.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    //Todos los vehículos
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    //Vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        return vehicle.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Vehículo por placa
    @GetMapping("/placa/{placa}")
    public ResponseEntity<Vehicle> getVehicleByPlaca(@PathVariable String placa) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleByPlaca(placa);
        return vehicle.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Vehículos por cliente
    @GetMapping("/customer/{customerId}")
    public List<Vehicle> getVehiclesByCustomer(@PathVariable Long customerId) {
        return vehicleService.getVehiclesByCustomer(customerId);
    }

    //Crear vehículo
    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    //Crear vehículo para cliente específico
    @PostMapping("/customer/{customerId}")
    public Vehicle createVehicleForCustomer(@PathVariable Long customerId, @RequestBody Vehicle vehicle) {
        return vehicleService.createVehicleForCustomer(customerId, vehicle);
    }
    //Eliminar vehículo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleService.deleteVehicle(id);
            return ResponseEntity.ok("Vehículo eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
