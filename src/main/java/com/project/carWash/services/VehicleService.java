package com.project.carWash.services;

import com.project.carWash.entity.Customer;
import com.project.carWash.entity.Vehicle;
import com.project.carWash.repository.CustomerRepository;
import com.project.carWash.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Obtener todos los vehículos
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Obtener vehículo por ID
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }
    // Obtener vehículo por placa
    public Optional<Vehicle> getVehicleByPlaca(String placa) {
        return vehicleRepository.findByPlaca(placa);
    }
    // Obtener vehículos por cliente
    public List<Vehicle> getVehiclesByCustomer(Long customerId) {
        return vehicleRepository.findByCustomerIdCustomer(customerId);
    }
    // Crear nuevo vehículo
    public Vehicle createVehicle(Vehicle vehicle) {

        return vehicleRepository.save(vehicle);
    }
    // Crear vehículo asociado a cliente
    public Vehicle createVehicleForCustomer(Long customerId, Vehicle vehicle) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + customerId));
        vehicle.setCustomer(customer);
        return vehicleRepository.save(vehicle);
    }

    // Eliminar vehículo
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));
        vehicleRepository.delete(vehicle);
    }
}
