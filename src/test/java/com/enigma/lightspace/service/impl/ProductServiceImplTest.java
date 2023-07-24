package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Category;
import com.enigma.lightspace.entity.Product;
import com.enigma.lightspace.entity.ProductPrice;
import com.enigma.lightspace.entity.Vendor;
import com.enigma.lightspace.entity.constant.ERole;
import com.enigma.lightspace.model.request.ProductRequest;
import com.enigma.lightspace.model.response.ProductResponse;
import com.enigma.lightspace.repository.ProductRepository;
import com.enigma.lightspace.service.CategoryService;
import com.enigma.lightspace.service.ProductPriceService;
import com.enigma.lightspace.service.VendorService;
import com.enigma.lightspace.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ValidationUtil validationUtil;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductPriceService productPriceService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private VendorService vendorService;
    private ProductServiceImpl productService;
    @BeforeEach
    void setUp() {
        this.productService = new ProductServiceImpl(validationUtil,productRepository,
                productPriceService,categoryService,vendorService);
    }

    @Test
    void testCreateProduct() {
//        ProductRequest request = new ProductRequest();
//        request.setName("Product A");
//        request.setPrice(1000L);
//        request.setStock(50);
//
//        doNothing().when(validationUtil).validate(request);
//        Vendor vendor = new Vendor();
//        vendor.setName("Vendor A");
//
//        Product product = Product.builder().id("1").product(request.getName()).build();
//        ProductPrice productPrice = ProductPrice.builder().price(request.getPrice()).vendor(vendor).stock(request.getStock()).build();
//
//        Authentication authentication = new TestingAuthenticationToken("vendor@example.com", "password");
//        when(vendorService.getByAuth(authentication)).thenReturn(vendor);
//
//        when(productRepository.saveAndFlush(product)).thenReturn(product);
//
//        when(productPriceService.save(productPrice)).thenReturn(productPrice);
//        ProductResponse result = productService.create(request, authentication);

    }


    @Test
    void createBulk() {
    }

    @Test
    void getAll() {
    }

    @Test
     void testGetByCode_NotFound() {
        when(productRepository.findByCodeAndStatus("NONEXISTENT", true)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> productService.getByCode("NONEXISTENT"));
    }

    @Test
    void testGetByCode() {
//        Product product = new Product();
//        product.setCode("PROD001");
//        product.setProduct("Product A");
//        product.setStatus(true);
//
//        ProductPrice productPrice = new ProductPrice();
//        productPrice.setProduct(product);
//        productPrice.setStock(100);
//        productPrice.setPrice(200L);
//        productPrice.setIsActive(true);
//
//        when(productRepository.findByCodeAndStatus("PROD001", true)).thenReturn(Optional.of(product));
//
//        when(productPriceService.productActive(product.getId(), true)).thenReturn(productPrice);
//
//        ProductResponse result = productService.getByCode("PROD001");
//
//        assertEquals("PROD001", result.getProductCode());
//        assertEquals("Product A", result.getName());
//        assertEquals(100, result.getStock());
//        assertEquals(200L, result.getPrice());
    }


    @Test
    void update() {
    }

    @Test
     void testDeleteByCode() {
        Product product = new Product();
        product.setCode("PROD001");
        product.setProduct("Product A");
        product.setStatus(true);

        when(productRepository.findByCodeAndStatus("PROD001", true)).thenReturn(Optional.of(product));

        productService.deleteByCode("PROD001");

        assertEquals(false, product.getStatus());
        verify(productRepository, times(1)).save(product);
    }
    @Test
    void testDeleteByCode_NotFound() {
        when(productRepository.findByCodeAndStatus("NONEXISTENT", true)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> productService.deleteByCode("NONEXISTENT"));
    }
}