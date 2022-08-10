package com.ps17931.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps17931.domain.Brand;
import com.ps17931.repository.BrandRepository;

@Service
public class BrandService {

    private final BrandRepository repo;

    @Autowired
    private BrandService(BrandRepository repo) {
        this.repo = repo;
    }

    public List<Brand> findAll() {
        return repo.findAll();
    }
}
