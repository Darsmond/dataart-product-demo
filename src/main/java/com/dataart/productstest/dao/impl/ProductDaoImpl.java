package com.dataart.productstest.dao.impl;

import com.dataart.productstest.dao.ProductDao;
import com.dataart.productstest.model.Product;
import com.dataart.productstest.model.ProductGroupCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<Product> productRowMapper;
    private final RowMapper<ProductGroupCount> productGroupCountRowMapper;

    @Override
    public List<ProductGroupCount> getProductGroups() {
        String query = "select tg.group_name, tg.group_id, count(tp.product_name) \n" +
                "as product_count from T_GROUP tg \n" +
                "left join T_PRODUCT tp on tg.group_id = tp.group_id group by tg.group_name, tg.group_id";
        return namedParameterJdbcTemplate.query(
                        query,
                        productGroupCountRowMapper)
                .stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(ProductGroupCount::getGroupName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProducts(Long productGroupId) {
        String query = "select product_id, product_name, product_price from T_PRODUCT where group_id = :groupId";
        return namedParameterJdbcTemplate.query(
                    query,
                    Collections.singletonMap("groupId", productGroupId),
                    productRowMapper)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }



}
