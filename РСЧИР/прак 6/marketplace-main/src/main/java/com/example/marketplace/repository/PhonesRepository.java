package com.example.marketplace.repository;

import com.example.marketplace.models.Phones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonesRepository extends JpaRepository<Phones, Integer> {
}
