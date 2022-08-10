package com.ps17931.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ps17931.domain.Cart;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

    List<Cart> getCartByUsername(String username);

    @Query("SELECT c FROM Cart c WHERE c.product.id=:id")
    Optional<Cart> findByProdId(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Cart c SET c.quantity=:qty WHERE c.id=:id")
    void updateQuantity(@Param("qty") int quantity, @Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE Cart c SET c.price=:price WHERE c.id=:id")
    void updatePrice(@Param("price") double price, @Param("id") String id);
}
