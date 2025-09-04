package com.exam.resourcebooker.controller;

import com.exam.resourcebooker.model.Booking;
import com.exam.resourcebooker.model.interfaces.BookingRepository;
import com.exam.resourcebooker.model.interfaces.UserRepository;
import com.exam.resourcebooker.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.exam.resourcebooker.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService service;
    private final UserRepository users;

    @Autowired
    private  BookingRepository bookingRepo;

    public BookingController(BookingService service, UserRepository users) { this.service = service; this.users = users; }

    @GetMapping
    public List<Booking> list(@RequestParam(required=false) @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                              @RequestParam(required=false) @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        return bookingRepo.findInRange(from, to);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Booking b, Authentication auth) {
        String email = (String)auth.getPrincipal();
        UUID userId = users.findByEmail(email).map(User::getId).orElseThrow();
        b.setUserId(userId);
        try { return ResponseEntity.ok(service.create(b)); }
        catch (IllegalArgumentException ex) { return ResponseEntity.status(409).body(ex.getMessage()); }
    }


}
