package com.enigma.lightspace.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderResponse {
    private String orderId;
    private String orderDate;
    private Long grandTotal;
    private String createdBy;
    private List<OrderDetailResponse> orderDetailResponse;
}
