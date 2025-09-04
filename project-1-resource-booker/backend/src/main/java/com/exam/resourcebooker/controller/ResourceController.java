package com.exam.resourcebooker.controller;

import com.exam.resourcebooker.model.Resource;
import com.exam.resourcebooker.model.interfaces.ResourceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private final ResourceRepository repo;
    public ResourceController(ResourceRepository repo) { this.repo = repo; }


    @GetMapping public List<Resource> list() { return repo.findAll(); }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Resource create(@RequestBody @Valid Resource r) { return repo.save(r); }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Resource update(@PathVariable UUID id, @RequestBody Resource r) {
        r.setId(id); return repo.save(r);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
