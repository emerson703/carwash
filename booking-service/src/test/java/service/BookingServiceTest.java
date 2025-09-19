package service;

import com.project.dto.BookingDTO;
import com.project.dto.BookingRequestDTO;
import com.project.entity.Booking;
import com.project.exception.BookingNotFoundException;
import com.project.repository.BookingRepository;
import com.project.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        booking.setId(1L);
        booking.setCustomerId(10L);
        booking.setCustomerName("Juan Pérez");
        booking.setVehicleType("Auto");
        booking.setCarLicense("ABC-123");
        booking.setBookingTime(LocalDateTime.now());
        booking.setWashType("Premium");
        booking.setStatus("PENDING");
    }

    @Test
    void testGetAllBookings() {
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking));

        List<BookingDTO> result = bookingService.getAllBookings();

        assertEquals(1, result.size());
        assertEquals("Juan Pérez", result.get(0).getCustomerName());
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void testGetBookingById() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        Optional<BookingDTO> result = bookingService.getBookingById(1L);

        assertTrue(result.isPresent());
        assertEquals("Juan Pérez", result.get().getCustomerName());
        verify(bookingRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateBooking() {
        BookingRequestDTO request = new BookingRequestDTO();
        request.setCustomerId(10L);
        request.setCustomerName("Juan Pérez");
        request.setVehicleType("Auto");
        request.setCarLicense("ABC-123");
        request.setBookingTime(LocalDateTime.now());
        request.setWashType("Premium");

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        BookingDTO result = bookingService.createBooking(request);

        assertNotNull(result);
        assertEquals("Juan Pérez", result.getCustomerName());
        assertEquals("PENDING", result.getStatus()); // por defecto
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void testDeleteBooking_WhenExists() {
        when(bookingRepository.existsById(1L)).thenReturn(true);

        boolean deleted = bookingService.deleteBooking(1L);

        assertTrue(deleted);
        verify(bookingRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBooking_WhenNotExists() {
        when(bookingRepository.existsById(1L)).thenReturn(false);
        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.deleteBooking(1L);
        });
        verify(bookingRepository, never()).deleteById(1L);
    }
}