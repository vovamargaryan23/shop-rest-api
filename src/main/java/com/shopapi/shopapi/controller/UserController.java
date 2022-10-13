package com.shopapi.shopapi.controller;

import com.shopapi.shopapi.entity.Product;
import com.shopapi.shopapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private ProductService productService;

    private final Set<Product> cart;

    public UserController() {
        this.cart = new HashSet<>();
    }

    @GetMapping("/products")
    public List<Product> findAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/cart")
    public Set<Product> getCart(){
        return cart;
    }

    @PostMapping("/cart")
    public void addToCart(@RequestBody Product product){
        cart.add(product);
    }

    @DeleteMapping("/cart/{productId}")
    public void removeFromCart(@PathVariable("productId") Long id){
        cart.removeIf(s -> Objects.equals(s.getProductId(), id));
    }

}
