package com.example.library_management_system.controller;

import com.example.library_management_system.dto.PatronDto;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.Patron;
import com.example.library_management_system.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return ResponseEntity.ok( patronService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(patronService.findAll());
    }

    @PostMapping()
    public void insert(@RequestBody @Valid PatronDto patronDto) {
        patronService.insert(patronDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id ,@RequestBody @Valid PatronDto patronDto) {
        return ResponseEntity.ok(patronService.update(id,patronDto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        patronService.deleteById(id);
    }
}
