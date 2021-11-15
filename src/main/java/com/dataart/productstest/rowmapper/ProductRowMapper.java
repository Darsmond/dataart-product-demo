package com.dataart.productstest.rowmapper;

import com.dataart.productstest.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getLong("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setProductPrice(rs.getLong("product_price"));
        return product;
    }
}
