package com.dataart.productstest.dao;

import com.dataart.productstest.model.Product;
import com.dataart.productstest.model.ProductGroupCount;

import java.util.List;

public interface ProductDao {

    List<ProductGroupCount> getProductGroups();

    List<Product> getProducts(Long productGroupId);
}
