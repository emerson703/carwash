package com.project.service;

import com.project.dto.BookingDTO;
import com.project.dto.BookingRequestDTO;
import com.project.entity.Booking;
import com.project.exception.BookingNotFoundException;
import com.project.exception.InvalidBookingException;
import com.project.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // Listar TODAS las reservas
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener reserva por ID
    public Optional<BookingDTO> getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(this::convertToDTO)
                .or(() -> {
                    throw new BookingNotFoundException(id);
                });
    }

    // Reservas por cliente
    public List<BookingDTO> getBookingsByCustomerId(Long customerId) {
        List<Booking> bookings = bookingRepository.findByCustomerId(customerId);

        if (bookings.isEmpty()) {
            throw new BookingNotFoundException("No se encontraron reservas para el cliente ID: " + customerId);
        }

        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //  Crear nueva reserva
    public BookingDTO createBooking(BookingRequestDTO bookingRequest) {
        //  Validaciones con excepciones personalizadas
        validateBookingRequest(bookingRequest);

        Booking booking = convertRequestToEntity(bookingRequest);
        booking.setStatus("PENDING"); // Estado por defecto
        Booking savedBooking = bookingRepository.save(booking);
        return convertToDTO(savedBooking);
    }

    // Eliminar reserva
    public boolean deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new BookingNotFoundException(id);
        }

        bookingRepository.deleteById(id);
        return true;
    }


    private void validateBookingRequest(BookingRequestDTO request) {
        if (request.getCustomerName() == null || request.getCustomerName().trim().isEmpty()) {
            throw new InvalidBookingException("El nombre del cliente es requerido");
        }

        if (request.getCarLicense() == null || request.getCarLicense().trim().isEmpty()) {
            throw new InvalidBookingException("La placa del vehículo es requerida");
        }

        if (request.getVehicleType() == null || request.getVehicleType().trim().isEmpty()) {
            throw new InvalidBookingException("El tipo de vehículo es requerido");
        }

        if (request.getWashType() == null || request.getWashType().trim().isEmpty()) {
            throw new InvalidBookingException("El tipo de lavado es requerido");
        }

        if (request.getBookingTime() == null) {
            throw new InvalidBookingException("La fecha y hora de la reserva son requeridas");
        }

        if (request.getCustomerId() == null) {
            throw new InvalidBookingException("El ID del cliente es requerido");
        }
    }

    //  MÉTODOS DE CONVERSIÓN

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setCustomerId(booking.getCustomerId());
        dto.setCustomerName(booking.getCustomerName());
        dto.setVehicleType(booking.getVehicleType());
        dto.setCarLicense(booking.getCarLicense());
        dto.setBookingTime(booking.getBookingTime());
        dto.setWashType(booking.getWashType());
        dto.setStatus(booking.getStatus());
        return dto;
    }

    private Booking convertRequestToEntity(BookingRequestDTO request) {
        Booking booking = new Booking();
        booking.setCustomerId(request.getCustomerId());
        booking.setCustomerName(request.getCustomerName());
        booking.setVehicleType(request.getVehicleType());
        booking.setCarLicense(request.getCarLicense());
        booking.setBookingTime(request.getBookingTime());
        booking.setWashType(request.getWashType());
        return booking;
    }
}