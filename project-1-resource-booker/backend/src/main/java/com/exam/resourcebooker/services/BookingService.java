package com.exam.resourcebooker.services;

import com.exam.resourcebooker.model.Booking;
import com.exam.resourcebooker.model.interfaces.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingRepository bookingRepo;
    public BookingService(BookingRepository bookingRepo) { this.bookingRepo = bookingRepo; }


    public Booking create(Booking b) {
        if (!bookingRepo.findOverlaps(b.getResourceId(), b.getStart(), b.getEnd()).isEmpty()) {
            throw new IllegalArgumentException("Overlapping booking");
        }
        b.setStatus("CONFIRMED");
        return bookingRepo.save(b);
    }
}
