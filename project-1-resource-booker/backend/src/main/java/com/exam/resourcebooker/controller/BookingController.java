package com.exam.resourcebooker.controller;

import com.exam.resourcebooker.model.Booking;
import com.exam.resourcebooker.model.interfaces.UserRepository;
import com.exam.resourcebooker.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exam.resourcebooker.model.User;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService service;
    private final UserRepository users;


    public BookingController(BookingService service, UserRepository users) { this.service = service; this.users = users; }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Booking b, Authentication auth) {
        String email = (String)auth.getPrincipal();
        UUID userId = users.findByEmail(email).map(User::getId).orElseThrow();
        b.setUserId(userId);
        try { return ResponseEntity.ok(service.create(b)); }
        catch (IllegalArgumentException ex) { return ResponseEntity.status(409).body(ex.getMessage()); }
    }
}
