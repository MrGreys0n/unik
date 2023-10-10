package com.example.marketplace.repository;

import com.example.marketplace.models.WashingMachines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WashingMachinesRepository extends JpaRepository<WashingMachines, Integer> {
}
