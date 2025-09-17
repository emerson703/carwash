package com.project.controller;

import com.project.dto.BookingDTO;
import com.project.dto.BookingRequestDTO;
import com.project.dto.JwtValidationResponse;
import com.project.service.AuthServiceClient;
import com.project.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final AuthServiceClient authServiceClient;

    public BookingController(BookingService bookingService, AuthServiceClient authServiceClient) {
        this.bookingService = bookingService;
        this.authServiceClient = authServiceClient;
    }

    // Listar TODAS las reservas
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        // Validar token si está presente
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            JwtValidationResponse validation = authServiceClient.validateToken(token);
            if (!validation.isValid()) {
                return ResponseEntity.status(401).build();
            }
        }

        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // Obtener reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        BookingDTO booking = bookingService.getBookingById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return ResponseEntity.ok(booking);
    }

    // Reservas por cliente
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByCustomerId(
            @PathVariable Long customerId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        // Validar token si está presente
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            JwtValidationResponse validation = authServiceClient.validateToken(token);
            if (!validation.isValid()) {
                return ResponseEntity.status(401).build();
            }
        }

        return ResponseEntity.ok(bookingService.getBookingsByCustomerId(customerId));
    }

    // Crear nueva reserva
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(
            @RequestBody BookingRequestDTO bookingRequest,
            @RequestHeader("Authorization") String authHeader) {

        // Validar token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String token = authHeader.substring(7);
        JwtValidationResponse validation = authServiceClient.validateToken(token);

        if (!validation.isValid()) {
            return ResponseEntity.status(401).build();
        }

        BookingDTO createdBooking = bookingService.createBooking(bookingRequest);
        return ResponseEntity.ok(createdBooking);
    }

    // Eliminar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {

        // Validar token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String token = authHeader.substring(7);
        JwtValidationResponse validation = authServiceClient.validateToken(token);

        if (!validation.isValid()) {
            return ResponseEntity.status(401).build();
        }

        bookingService.deleteBooking(id);
        return ResponseEntity.ok().build();
    }

    // Test endpoint
    @GetMapping("/test")
    public String test() {
        return "Booking Service is working with Feign!";
    }
}