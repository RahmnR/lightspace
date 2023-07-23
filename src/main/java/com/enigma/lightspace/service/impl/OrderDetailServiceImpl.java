package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.OrderDetail;
import com.enigma.lightspace.repository.OrderDetailRepository;
import com.enigma.lightspace.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return orderDetailRepository.saveAndFlush(orderDetail);
    }
}
