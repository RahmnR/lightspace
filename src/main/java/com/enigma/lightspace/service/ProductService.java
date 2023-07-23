package com.enigma.lightspace.service;

import com.enigma.lightspace.entity.Product;
import com.enigma.lightspace.model.request.ProductRequest;
import com.enigma.lightspace.model.response.ProductResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request, Authentication authentication);
    List<ProductResponse> createBulk(List<ProductRequest> products, Authentication authentication);
    List<ProductResponse> getAll();
    ProductResponse getByCode(String code);
    Product getProductByCode(String code);
    //    Page<ProductResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size);
    ProductResponse update(ProductRequest product);
    void deleteByCode(String id);
}
