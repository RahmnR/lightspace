package com.enigma.lightspace.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductRequest {
    private String productId;
    private String name;
    private String Category;
    private Integer stock;
    private Long price;
    private String storeId;

}
