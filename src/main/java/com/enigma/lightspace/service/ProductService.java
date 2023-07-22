package com.enigma.lightspace.service;

import com.enigma.lightspace.model.request.ProductRequest;
import com.enigma.lightspace.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    List<ProductResponse> createBulk(List<ProductRequest> products);
    ProductResponse getById(String id);
    //    Page<ProductResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size);
    ProductResponse update(ProductRequest product);
    void deleteById(String id);
}
