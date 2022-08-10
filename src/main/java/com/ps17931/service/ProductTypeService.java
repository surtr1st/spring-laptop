package com.ps17931.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps17931.domain.ProductType;
import com.ps17931.repository.ProductTypeRepository;

@Service
public class ProductTypeService {

    private final ProductTypeRepository repo;

    @Autowired
    private ProductTypeService(ProductTypeRepository repo) {
        this.repo = repo;
    }

    public List<ProductType> findAll() {
        return repo.findAll();
    }
}
