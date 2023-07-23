package com.enigma.lightspace.controller;

import com.enigma.lightspace.model.request.OrderRequest;
import com.enigma.lightspace.model.response.CommonResponse;
import com.enigma.lightspace.model.response.OrderResponse;
import com.enigma.lightspace.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/transactions")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<CommonResponse<OrderResponse>> create(@RequestBody OrderRequest request){
        OrderResponse orderResponse = orderService.create(request);
        CommonResponse<OrderResponse> commonResponse = CommonResponse.<OrderResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Create success")
                .data(orderResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getTransactions() {
        List<OrderResponse> orderResponses = orderService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all transaction")
                        .data(orderResponses)
                        .build());
    }

}