package com.enigma.lightspace.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {
    private String productId;
    private String name;
    private String Category;
    private Integer stock;
    private Long price;

}
