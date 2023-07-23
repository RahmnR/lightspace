package com.enigma.lightspace.service;

import com.enigma.lightspace.model.request.OrderRequest;
import com.enigma.lightspace.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    public OrderResponse create(OrderRequest request);
    public List<OrderResponse> getAll();
}
