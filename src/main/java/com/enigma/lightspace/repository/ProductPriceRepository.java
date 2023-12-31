package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductPriceRepository extends JpaRepository<ProductPrice,String> {
    Optional<ProductPrice> findByProduct_IdAndIsActive(String productId, Boolean isActive);
    Optional<ProductPrice> findByProduct_IdAndIsActiveAndVendor_Id(String productId, Boolean isActive, String vendorId);

}
