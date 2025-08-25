package com.exam.resourcebooker.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name="resource_id", nullable=false)
    private UUID resourceId;
    @Column(name="user_id", nullable=false)
    private UUID userId;
    @Column(name="start_ts", nullable=false)
    private java.time.LocalDateTime start;
    @Column(name="end_ts", nullable=false)
    private java.time.LocalDateTime end;
    @Column(nullable=false)
    private String status; // PENDING/CONFIRMED/CANCELLED

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getResourceId() {
        return resourceId;
    }

    public void setResourceId(UUID resourceId) {
        this.resourceId = resourceId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // getters/setters
}
