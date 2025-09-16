package com.project.service;
import com.project.dto.BookingDTO;
import com.project.dto.BookingRequestDTO;
import com.project.entity.Booking;
import com.project.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    //private final BookingMapper bookingMapper;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // 1. Listar TODAS las reservas
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 2. Obtener reserva por ID
    public Optional<BookingDTO> getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(this::convertToDTO);
    }

    // 3. Reservas por cliente
    public List<BookingDTO> getBookingsByCustomerId(Long customerId) {
        return bookingRepository.findByCustomerId(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 4. Crear nueva reserva
    public BookingDTO createBooking(BookingRequestDTO bookingRequest) {
        Booking booking = convertRequestToEntity(bookingRequest);
        booking.setStatus("PENDING"); // Estado por defecto
        Booking savedBooking = bookingRepository.save(booking);
        return convertToDTO(savedBooking);
    }

    // 5. Eliminar reserva
    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ===== MÉTODOS DE CONVERSIÓN MANUALES =====

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setCustomerId(booking.getCustomerId());
        dto.setCustomerName(booking.getCustomerName());
        dto.setVehicleType(booking.getVehicleType());
        dto.setPlaca(booking.getPlaca());
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
        booking.setPlaca(request.getPlaca());
        booking.setBookingTime(request.getBookingTime());
        booking.setWashType(request.getWashType());
        return booking;
    }
}
