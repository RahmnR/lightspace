package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Product;
import com.enigma.lightspace.model.request.ProductRequest;
import com.enigma.lightspace.model.response.ProductResponse;
import com.enigma.lightspace.repository.ProductRepository;
import com.enigma.lightspace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductResponse create(ProductRequest request) {
//        Product.builder()
//                .product(request.getName())
//                .category().build();
//



        return null;
    }

    @Override
    public List<ProductResponse> createBulk(List<ProductRequest> products) {
        return null;
    }

    @Override
    public ProductResponse getById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product!=null)return ProductResponse.builder().build();
        return null;
    }

    @Override
    public ProductResponse update(ProductRequest product) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
