package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.OrderDetail;
import com.enigma.lightspace.repository.OrderDetailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderDetailServiceImplTest {

    @Mock
    private OrderDetailRepository orderDetailRepository;
    private OrderDetailServiceImpl orderDetailService;
    @Test
    void testCreateOrderDetail() {
        orderDetailService = new OrderDetailServiceImpl(orderDetailRepository);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId("1");
        orderDetail.setQuantity(5);

        when(orderDetailRepository.saveAndFlush(any(OrderDetail.class))).thenReturn(orderDetail);

        OrderDetail result = orderDetailService.create(orderDetail);

        assertEquals("1", result.getId());
        assertEquals(5, result.getQuantity());
    }

}