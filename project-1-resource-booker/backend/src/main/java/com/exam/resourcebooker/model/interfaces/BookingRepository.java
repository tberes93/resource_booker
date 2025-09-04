package com.exam.resourcebooker.model.interfaces;

import com.exam.resourcebooker.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    @Query("select b from Booking b where b.resourceId = :rid and b.end > :start and b.start < :end")
    List<Booking> findOverlaps(@Param("rid") UUID resourceId,
                               @Param("start") LocalDateTime start,
                               @Param("end") LocalDateTime end);

    @Query("select b from Booking b where (:from is null or b.end > :from) and (:to is null or b.start < :to)")
    List<Booking> findInRange(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
