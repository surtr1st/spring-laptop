package com.ps17931.controller;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps17931.domain.Brand;
import com.ps17931.domain.Product;
import com.ps17931.domain.ProductType;
import com.ps17931.service.BrandService;
import com.ps17931.service.ProductService;
import com.ps17931.service.ProductTypeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest")
public class ProductRestController {
    
    private final ProductService service;
    private final BrandService brandService;
    private final ProductTypeService prodTypeService;
    private final ObjectMapper mapper;

    @Autowired
    private ProductRestController(
        ProductService service,
        BrandService brandService,
        ProductTypeService productTypeService,
        ObjectMapper mapper
    ) {
        this.service = service;
        this.brandService = brandService;
        this.prodTypeService = productTypeService;
        this.mapper = mapper;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> retrieve() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> retrieveBrands() {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/product-types")
    public ResponseEntity<List<ProductType>> retrieveProductTypes() {
        return new ResponseEntity<>(prodTypeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create-product")
    public ResponseEntity<Product> createProduct(@RequestBody JsonNode productNode) {
        Product prod = mapper.convertValue(productNode, Product.class);
        service.create(prod);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @PutMapping("/update-product")
    public ResponseEntity<Product> updateProduct(@RequestBody JsonNode productNode) {
        Product prod = mapper.convertValue(productNode, Product.class);
        service.update(prod);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @GetMapping("/edit-product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id) {
        Product product = service.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
