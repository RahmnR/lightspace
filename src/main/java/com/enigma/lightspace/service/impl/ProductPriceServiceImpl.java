package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.ProductPrice;
import com.enigma.lightspace.repository.ProductPriceRepository;
import com.enigma.lightspace.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {
    private final ProductPriceRepository productPriceRepository;
    @Override
    public ProductPrice save(ProductPrice productPrice) {
        return productPriceRepository.save(productPrice);
    }
}
