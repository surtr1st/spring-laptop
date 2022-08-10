package com.ps17931.controller;


import com.ps17931.domain.Brand;
import com.ps17931.domain.Product;
import com.ps17931.service.BrandService;
import com.ps17931.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;
    private final BrandService brandService;

    @Autowired
    private ProductController(ProductService service, BrandService brandService) {
        this.service = service;
        this.brandService = brandService;
    }

    @ModelAttribute("products")
    List<Product> products() {
        return service.findAll();
    }

    @ModelAttribute("brands")
    List<Brand> brands() {
        return brandService.findAll();
    }

    @RequestMapping()
    public String view() {
        return "product/view";
    }

    @RequestMapping("brand/{name}")
    public String searchByBrand(@PathVariable("name") String name, Model model) {
        model.addAttribute("products", service.searchByBrand(name));
        return view();
    }

    @RequestMapping("/price-range")
    public String searchByPrice(
            @RequestParam("from") Optional<Double> from,
            @RequestParam("to") Optional<Double> to,
            Model model) {
        NumberFormat format = new DecimalFormat("#0");
        double min = from.orElse(Double.MIN_VALUE);
        double max = to.orElse(Double.MAX_VALUE);
        model.addAttribute("min", format.format(min));
        model.addAttribute("max", format.format(max));
        model.addAttribute("products", service.searchByPriceRange(min, max));
        return view();
    }
}
