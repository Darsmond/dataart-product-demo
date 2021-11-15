package com.dataart.productstest.service;

import com.dataart.productstest.dao.ProductDao;
import com.dataart.productstest.model.Product;
import com.dataart.productstest.model.ProductGroupCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    private List<ProductGroupCount> productGroupCounts;
    private List<Product> products;
    private boolean isSortedByName;
    private boolean isSortedByPrice;

    public List<ProductGroupCount> getProductGroups() {
        if (productGroupCounts == null) {
            log.info("productGroupCounts has been loaded");
            productGroupCounts = productDao.getProductGroups();
        }

        return productGroupCounts;
    }

    public List<Product> getProductsByGroupId(Long productGroupId) {
        log.info("getting products for productGroupId: {}", productGroupId);
        products = productDao.getProducts(productGroupId);
        return products;
    }

    public List<Product> getBatchOfProducts(int start) {
        return products.subList(start, start+10);
    }

    public List<Product> sortProducts(String sortingField) {
        List<Product> sortedList;
        if (products == null) {
            return List.of();
        }
        if ("product_name".equals(sortingField)) {
            sortedList = products.stream()
                    .sorted(Comparator.comparing(Product::getProductName))
                    .collect(Collectors.toList());
            if (isSortedByName) {
                Collections.reverse(sortedList);
                isSortedByName = false;
            } else {
                log.info("list was sorted by name");
                isSortedByName = true;
            }
        } else {
            sortedList = products.stream()
                    .sorted(Comparator.comparing(Product::getProductPrice))
                    .collect(Collectors.toList());
            if (isSortedByPrice) {
                Collections.reverse(sortedList);
                isSortedByPrice = false;
            } else {
                log.info("list was sorted by price");
                isSortedByPrice = true;
            }
        }
        return sortedList;
    }
}
