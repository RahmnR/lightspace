package com.enigma.lightspace.service;

import com.enigma.lightspace.model.request.OrderRequest;
import com.enigma.lightspace.model.response.OrderResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface OrderService {
    public OrderResponse create(OrderRequest request);

    public List<OrderResponse> getAll();

    public void createCsv(HttpServletResponse response);
}