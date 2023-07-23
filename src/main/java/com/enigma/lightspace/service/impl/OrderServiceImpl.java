package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.Order;
import com.enigma.lightspace.entity.OrderDetail;
import com.enigma.lightspace.entity.Product;
import com.enigma.lightspace.entity.ProductPrice;
import com.enigma.lightspace.model.request.OrderDetailRequest;
import com.enigma.lightspace.model.request.OrderRequest;
import com.enigma.lightspace.model.response.OrderDetailResponse;
import com.enigma.lightspace.model.response.OrderResponse;
import com.enigma.lightspace.model.response.ProductResponse;
import com.enigma.lightspace.repository.OrderRepository;
import com.enigma.lightspace.service.OrderDetailService;
import com.enigma.lightspace.service.OrderService;
import com.enigma.lightspace.service.ProductPriceService;
import com.enigma.lightspace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;
    private final ProductPriceService productPriceService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public OrderResponse create(OrderRequest request) {
        Order order = Order.builder().build();
        orderRepository.saveAndFlush(order);

        List<OrderDetail> orderDetails = request.getOrder().stream().map(orderDetailRequest -> {
            Product product = productService.getProductByCode(orderDetailRequest.getProductCode());
            ProductPrice productPrice = productPriceService.productActive(product.getId(), true);

            productPrice.setStock(productPrice.getStock() - orderDetailRequest.getQuantiry());
            productPriceService.save(productPrice);

            OrderDetail orderDetail = OrderDetail.builder()
                    .order(order)
                    .productPrice(productPrice)
                    .quantity(orderDetailRequest.getQuantiry())
                    .build();
            return orderDetailService.create(orderDetail);

        }).collect(Collectors.toList());
        return toOrderResponse(order,orderDetails);
    }

    @Override
    public List<OrderResponse> getAll() {
        return null;
    }
    private OrderResponse toOrderResponse(Order order, List<OrderDetail> orderDetails){
        List<OrderDetailResponse> detailResponses = orderDetails.stream().map(orderDetail -> {

            return OrderDetailResponse.builder()
                    .productPrice(orderDetail.getProductPrice())
                    .quantity(orderDetail.getQuantity())
                    .subTotal(orderDetail.getProductPrice().getPrice() * orderDetail.getQuantity())
                    .build();
        }).collect(Collectors.toList());

        return OrderResponse.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .orderDetailResponse(detailResponses)
                .grandTotal(detailResponses.stream().mapToLong(OrderDetailResponse::getSubTotal).sum())
                .build();
    }
}
