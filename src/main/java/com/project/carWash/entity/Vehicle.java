package com.project.carWash.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVehicle")
    private Long id;

    @Column(name = "placa", nullable = false, unique = true, length = 20)
    private String placa;
    @Column(name = "brand", nullable = false, length = 50)
    private String brand;
    @Column(name = "model", nullable = false, length = 50)
    private String model;
    @Column(name = "type", nullable = false, length = 50)
    private String Type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCustomer", nullable = false)
    private Customer customer;

    public Vehicle (){}

    public Vehicle(String placa, String brand, String model, String type, Customer customer) {
        this.placa = placa;
        this.brand = brand;
        this.model = model;
        this.Type = type;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
