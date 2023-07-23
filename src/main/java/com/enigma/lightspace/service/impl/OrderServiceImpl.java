package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.*;
import com.enigma.lightspace.model.request.OrderRequest;
import com.enigma.lightspace.model.response.OrderDetailResponse;
import com.enigma.lightspace.model.response.OrderResponse;
import com.enigma.lightspace.repository.OrderRepository;
import com.enigma.lightspace.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;
    private final ProductPriceService productPriceService;
    private final WarehouseService warehouseService;
    private final ObjectMapper objectMapper;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public OrderResponse create(OrderRequest request) {
        Order order = Order.builder()
//                .createAt(Date.from(Instant.now()))
                .build();
        Order savedOrder = orderRepository.saveAndFlush(order);

        List<OrderDetailResponse> detailResponses = request.getOrder().stream().map(orderDetailRequest -> {
            Product product = productService.getProductByCode(orderDetailRequest.getProductCode());
            ProductPrice productPrice = productPriceService.productActive(product.getId(), true);
            if (productPrice.getStock() - orderDetailRequest.getQuantiry() < 0)
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Transaction Denied");

            Warehouse warehouse = Warehouse.builder()
                    .code(product.getCode())
                    .product(product.getProduct())
                    .stock(orderDetailRequest.getQuantiry())
                    .build();
            warehouseService.update(warehouse);

            productPrice.setStock(productPrice.getStock() - orderDetailRequest.getQuantiry());
            productPriceService.save(productPrice);
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(savedOrder)
                    .productPrice(productPrice)
                    .quantity(orderDetailRequest.getQuantiry())
                    .build();
            orderDetailService.create(orderDetail);

            return OrderDetailResponse.builder()
                    .product(orderDetail.getProductPrice().getProduct().getProduct())
                    .quantity(orderDetail.getQuantity())
                    .subTotal(orderDetail.getProductPrice().getPrice() * orderDetail.getQuantity())
                    .build();

        }).collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = order.getCreateAt().format(formatter);

        return OrderResponse.builder()
                .orderId(order.getId())
                .orderDate(date)
                .createdBy(order.getCreatedBy())
                .orderDetailResponse(detailResponses)
                .grandTotal(detailResponses.stream().mapToLong(OrderDetailResponse::getSubTotal).sum())
                .build();
    }

    @Override
    public List<OrderResponse> getAll() {
        List<Order> orders = orderRepository.findAll();

        return  orders.stream().map(order -> {
            return toOrderResponse(order, order.getOrderDetails());
        }).collect(Collectors.toList());
    }

    private OrderResponse toOrderResponse(Order order, List<OrderDetail> orderDetails){
        List<OrderDetailResponse> detailResponses = orderDetails.stream().map(orderDetail -> {

            return OrderDetailResponse.builder()
                    .product(orderDetail.getProductPrice().getProduct().getProduct())
                    .quantity(orderDetail.getQuantity())
                    .subTotal(orderDetail.getProductPrice().getPrice() * orderDetail.getQuantity())
                    .build();
        }).collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = order.getCreateAt().format(formatter);

        return OrderResponse.builder()
                .orderId(order.getId())
                .orderDetailResponse(detailResponses)
                .orderDate(date)
                .createdBy(order.getCreatedBy())
                .grandTotal(detailResponses.stream().mapToLong(OrderDetailResponse::getSubTotal).sum())
                .build();
    }
}
