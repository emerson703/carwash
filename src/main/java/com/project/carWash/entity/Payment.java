package com.project.carWash.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPayment")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOrder", nullable = false, unique = true)
    private Order order;

    @Column(name = "totatAmount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "paymentMethod", nullable = false, length = 50)
    private String paymentMethod;

    @Column(name = "paymentDate", nullable = false)
    private LocalDate paymentDate;


    public Payment() {
        this.paymentDate = LocalDate.now();
    }

    public Payment(Order order, BigDecimal totalAmount, String paymentMethod, LocalDate paymentDate) {
        this.order = order;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
