package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.ProductPrice;
import com.enigma.lightspace.repository.ProductPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductPriceServiceImplTest {
    @Mock
    private ProductPriceRepository productPriceRepository;
    private ProductPriceServiceImpl productPriceService;

    @BeforeEach
    void setUp() {
        productPriceService = new ProductPriceServiceImpl(productPriceRepository);
    }

    @Test
    void testSaveProductPrice() {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setId("priceId");
        productPrice.setPrice(100L);
        productPrice.setStock(50);

        when(productPriceRepository.saveAndFlush(any(ProductPrice.class))).thenReturn(productPrice);

        ProductPrice result = productPriceService.save(productPrice);

        assertEquals("priceId", result.getId());
        assertEquals(100L, result.getPrice());
        assertEquals(50, result.getStock());
    }

    @Test
    void testGetById() {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setId("priceId");
        productPrice.setPrice(100L);
        productPrice.setStock(50);

        when(productPriceRepository.findById("priceId")).thenReturn(Optional.of(productPrice));

        ProductPrice result = productPriceService.getById("priceId");

        assertEquals("priceId", result.getId());
        assertEquals(100L, result.getPrice());
        assertEquals(50, result.getStock());
    }

    @Test
    void testProductActive() {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setId("priceId");
        productPrice.setPrice(100L);
        productPrice.setStock(50);
        productPrice.setIsActive(true);

        when(productPriceRepository.findByProduct_IdAndIsActive("productId", true))
                .thenReturn(Optional.of(productPrice));

        ProductPrice result = productPriceService.productActive("productId", true);

        assertEquals("priceId", result.getId());
        assertEquals(100L, result.getPrice());
        assertEquals(50, result.getStock());
    }
    @Test
    void testProductActive_NotFound() {
        when(productPriceRepository.findByProduct_IdAndIsActive("nonexistentProductId", true))
                .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () ->
                productPriceService.productActive("nonexistentProductId", true));
    }


}