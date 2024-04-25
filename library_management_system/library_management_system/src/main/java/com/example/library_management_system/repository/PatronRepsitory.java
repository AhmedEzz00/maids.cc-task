package com.example.library_management_system.repository;

import com.example.library_management_system.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronRepsitory extends JpaRepository<Patron,Long> {

    Optional<Patron> findByEmail(String email);

    Optional<Patron> findByMobileNumber(String mobileNumber);
}
