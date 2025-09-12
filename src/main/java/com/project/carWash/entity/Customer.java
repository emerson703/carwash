package com.project.carWash.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCustomer")
    private Long idCustomer;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "LastName", nullable = false, length = 100)
    private String LastName;
    @Column(name = "DNI", nullable = false, unique = true, length = 20)
    private String DNI;
    @Column(name = "phone", length = 20)
    private String phone;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "address", length = 150)
    private String address;
    @Column(name = "registrationDate", nullable = false)
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vehicle> vehicles = new ArrayList<>();

    public Customer() {
        this.registrationDate = LocalDate.now();
    }

    public Customer(String name, String lastName, String DNI, String phone, String email, String address) {
        this.name = name;
        LastName = lastName;
        this.DNI = DNI;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
