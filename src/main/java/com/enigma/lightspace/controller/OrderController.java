package com.enigma.lightspace.controller;

import com.enigma.lightspace.model.request.OrderRequest;
import com.enigma.lightspace.model.response.CommonResponse;
import com.enigma.lightspace.model.response.OrderResponse;
import com.enigma.lightspace.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
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
    public ResponseEntity<?> getTransactions(@RequestParam(name = "day",required = false)Integer day,
                                             @RequestParam(name = "month",required = false)Integer month) {
        List<OrderResponse> orderResponses = orderService.searchBy(day,month);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all transaction")
                        .data(orderResponses)
                        .build());
    }
    @GetMapping("/csv")
    public ResponseEntity<?> getCsv(HttpServletResponse response) {
        orderService.createCsv(response);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully export csv")
                        .build());
    }

}