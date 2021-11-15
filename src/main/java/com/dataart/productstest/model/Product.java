package com.dataart.productstest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

    private Long productId;
    private String productName;
    private Long groupId;
    private Long productPrice;
}
