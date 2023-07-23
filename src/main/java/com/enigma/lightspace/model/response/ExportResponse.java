package com.enigma.lightspace.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ExportResponse {
    private String code;
    private String date;
    private String vendorName;
    private String productName;
    private String category;
    private Long price;
    private Integer quantity;
    private Long subPrice;
}
