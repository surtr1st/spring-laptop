package com.ps17931.controller;

import com.ps17931.service.AccountService;
import com.ps17931.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class DashboardRestController {

    private final ProductService productService;
    private final AccountService accountService;

    @Autowired
    private DashboardRestController(ProductService productService, AccountService accountService) {
        this.productService = productService;
        this.accountService = accountService;
    }

    @GetMapping("/total-product")
    public int totalProduct() {
        return productService.findAll().size();
    }

    @GetMapping("/total-user")
    public int totalUser() {
        return accountService.findAll().size();
    }

    @GetMapping("/tables")
    public ResponseEntity<List<String>> tables() {
        List<String> total = List.of(
                "Accounts", "Authorities", "Brands", "Carts", "Products", "ProductTypes", "Receipts", "Roles"
        );
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
