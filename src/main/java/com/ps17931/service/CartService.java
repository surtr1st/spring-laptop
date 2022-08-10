package com.ps17931.service;

import com.ps17931.domain.Cart;
import com.ps17931.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository repo;

    @Autowired
    private CartService(CartRepository repo) {
        this.repo = repo;
    }

    public List<Cart> findAll() {
        return repo.findAll();
    }

    public List<Cart> retrieveCart(String username) {
        return repo.getCartByUsername(username);
    }

    public void addCart(Cart cart) {
        repo.save(cart);
    }

    public Optional<Cart> findByProdId(int id) {
        return repo.findByProdId(id);
    }

    public Cart findById(String id) {
        return repo.findById(id).get();
    }

    public void updatePrice(double price, String id) {
        repo.updatePrice(price, id);
    }

    public void updateQuantity(int quantity, String id) {
        repo.updateQuantity(quantity, id);
    }

    public void deleteCart(String id) {
        repo.deleteById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }
}
