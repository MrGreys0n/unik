package com.example.marketplace.repository;

import com.example.marketplace.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Warehouse findByIdProductAndTypeOfProduct(int idProduct, String typeOfProduct);
}
