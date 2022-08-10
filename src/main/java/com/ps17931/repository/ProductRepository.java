package com.ps17931.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ps17931.domain.Product;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.brand=:name")
    List<Product> findByCompany(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :from AND :to")
    List<Product> searchByPrice(@Param("from") double from, @Param("to") double to);
}
