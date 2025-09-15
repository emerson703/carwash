package com.project.carWash.controllers;

import com.project.carWash.entity.Services;
import com.project.carWash.services.ServicecarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServicecarService servicecarService;

    public ServiceController(ServicecarService serviceService) {
        this.servicecarService = serviceService;
    }

    // GET all
    @GetMapping
    public List<Services> getAllServices() {
        return servicecarService.getAllServices();
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Services> getServiceById(@PathVariable Long id) {
        return servicecarService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public Services createService(@RequestBody Services service) {
        return servicecarService.createService(service);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        try {
            servicecarService.deleteService(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
