package com.exam.resourcebooker.controller;

import com.exam.resourcebooker.model.Resource;
import com.exam.resourcebooker.model.interfaces.ResourceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private final ResourceRepository repo;
    public ResourceController(ResourceRepository repo) { this.repo = repo; }


    @GetMapping
    public List<Resource> list() { return repo.findAll(); }
}
