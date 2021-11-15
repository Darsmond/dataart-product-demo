package com.dataart.productstest.rowmapper;

import com.dataart.productstest.model.ProductGroupCount;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductGroupCountRowMapper implements RowMapper<ProductGroupCount> {

    @Override
    public ProductGroupCount mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductGroupCount productGroupCount = new ProductGroupCount();
        productGroupCount.setGroupId(rs.getLong("group_id"));
        productGroupCount.setGroupName(rs.getString("group_name"));
        productGroupCount.setProductCount(rs.getLong("product_count"));
        return productGroupCount;
    }
}
