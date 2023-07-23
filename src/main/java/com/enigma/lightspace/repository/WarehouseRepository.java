package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.Category;
import com.enigma.lightspace.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse,String > {
    Optional<Warehouse> findWarehouseByCode(String code);

}
