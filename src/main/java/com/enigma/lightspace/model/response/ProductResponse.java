package com.enigma.lightspace.model.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {
    private String productId;
    private String productCode;
    private String name;
    private String Category;
    private Integer stock;
    private Long price;
    private String vendor;
}
