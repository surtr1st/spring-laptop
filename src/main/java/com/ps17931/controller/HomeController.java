package com.ps17931.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ps17931.domain.Product;
import com.ps17931.service.ProductService;

@Controller
public class HomeController {

    private final ProductService service;

    @Autowired
    private HomeController(ProductService service) {
        this.service = service;
    }
    
    @RequestMapping("index")
    public String index() {
        return "views/index";
    }

    @ModelAttribute("products")
    public List<Product> products() {
        return service.findAll();
    }
}
