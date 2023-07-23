package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.ProductPrice;
import com.enigma.lightspace.repository.ProductPriceRepository;
import com.enigma.lightspace.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {
    private final ProductPriceRepository productPriceRepository;

    @Override
    public ProductPrice save(ProductPrice productPrice) {
        return productPriceRepository.saveAndFlush(productPrice);
    }

    @Override
    public ProductPrice getById(String id) {
        return productPriceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Price not found"));
    }

    @Override
    public ProductPrice productActive(String productId, Boolean active) {
        return productPriceRepository.findByProduct_IdAndIsActive(productId,active).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Price not found"));
    }
}
