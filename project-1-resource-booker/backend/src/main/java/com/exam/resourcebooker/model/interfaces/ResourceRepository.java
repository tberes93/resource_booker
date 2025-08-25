package com.exam.resourcebooker.model.interfaces;

import com.exam.resourcebooker.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResourceRepository extends JpaRepository<Resource, UUID> {
    List<Resource> findAll();
}
