package com.dataart.productstest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductGroupCount {

    private Long groupId;
    private String groupName;
    private Long productCount;
}
