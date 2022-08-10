package com.ps17931.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps17931.domain.Product;
import com.ps17931.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    @Autowired
    private ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Product findById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product create(Product product) {
        return repo.save(product);
    }

    public Product update(Product product) {
        return repo.save(product);
    }
    
    public void delete(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchByBrand(String name) {
        return repo.findByCompany(name);
    }
    public List<Product> searchByPriceRange(double min, double max) {
        return repo.searchByPrice(min, max);
    }
}
