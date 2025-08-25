package com.exam.resourcebooker.controller;

import com.exam.resourcebooker.model.Booking;
import com.exam.resourcebooker.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService service;
    public BookingController(BookingService service) { this.service = service; }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Booking b) {
        try { return ResponseEntity.ok(service.create(b)); }
        catch (IllegalArgumentException ex) { return ResponseEntity.status(409).body(ex.getMessage()); }
    }
}
