package com.enigma.lightspace.model.response;

import com.enigma.lightspace.entity.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDetailResponse {
    private String product;
    private Integer quantity;
    private Long subTotal;
}
