package com.grievancetrack.controller;

import com.grievancetrack.entity.Citizen;
import com.grievancetrack.service.CitizenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizens")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @PostMapping
    public ResponseEntity<Citizen> createCitizen(@Valid @RequestBody Citizen citizen) {
        return new ResponseEntity<>(citizenService.createCitizen(citizen), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Citizen>> getAllCitizens() {
        return ResponseEntity.ok(citizenService.getAllCitizens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Citizen> getCitizenById(@PathVariable Long id) {
        return ResponseEntity.ok(citizenService.getCitizenById(id));
    }
}
