package com.dataart.productstest.controller;

import com.dataart.productstest.service.ProductService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/groups")
    public String getGroups(Model model) {
        var productGroupCounts = productService.getProductGroups();
        model.addAttribute("productGroupCounts", productGroupCounts);
        return "productPage";
    }

    @GetMapping("/products/{id}")
    public String getProducts(@PathVariable Long id, Model model) {
        var productGroupCounts = productService.getProductGroups();
        var products = productService.getProductsByGroupId(id);
        model.addAttribute("productGroupCounts", productGroupCounts);
        model.addAttribute("products", products);
        return "productPage";
    }

    @PostMapping("/scroll/{start}")
    public String getProductsForPage(@PathVariable int start, Model model) {
        var productsBatch = productService.getBatchOfProducts(start);
        var productGroupCounts = productService.getProductGroups();
        model.addAttribute("productGroupCounts", productGroupCounts);
        model.addAttribute("products", productsBatch);
        return "productPage";
    }

    @PostMapping("/sort/{sortField}")
    public String sortProducts(@PathVariable String sortField, Model model) {
        var sortedProducts = productService.sortProducts(sortField);
        var productGroupCounts = productService.getProductGroups();
        model.addAttribute("productGroupCounts", productGroupCounts);
        model.addAttribute("products", sortedProducts);
        return "productPage";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error("error occured during service work: {}", e.getMessage());
        return "errorPage";
    }
}
