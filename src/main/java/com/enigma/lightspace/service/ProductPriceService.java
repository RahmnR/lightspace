package com.enigma.lightspace.service;

import com.enigma.lightspace.entity.Product;
import com.enigma.lightspace.entity.ProductPrice;
import com.enigma.lightspace.model.request.ProductRequest;
import com.enigma.lightspace.model.response.ProductResponse;

import java.util.List;

public interface ProductPriceService {
    ProductPrice save(ProductPrice productPrice);

}
