package com.project.carWash.services;

import com.project.carWash.entity.Services;
import com.project.carWash.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicecarService {
    private final ServiceRepository serviceRepository;

    public ServicecarService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<Services> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    public Services createService(Services services) {
        return serviceRepository.save(services);
    }
    public void deleteService(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new RuntimeException("Services no encontrado con el id: " + id);
        }
        serviceRepository.deleteById(id);
    }
}
