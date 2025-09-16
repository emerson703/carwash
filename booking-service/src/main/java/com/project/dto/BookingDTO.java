package com.project.dto;

import lombok.Data;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

public class BookingDTO {
    private Long id;
    private Long customerId;
    private String customerName;
    private String vehicleType;
    private String placa;
    private LocalDateTime bookingTime;
    private String washType;
    private String status;

    // Getters y setters manuales
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }

    public String getWashType() { return washType; }
    public void setWashType(String washType) { this.washType = washType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
