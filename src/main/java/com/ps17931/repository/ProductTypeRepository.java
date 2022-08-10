package com.ps17931.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ps17931.domain.ProductType;


@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, String> {

}
