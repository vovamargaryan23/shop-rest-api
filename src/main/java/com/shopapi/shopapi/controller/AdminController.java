package com.shopapi.shopapi.controller;

import com.shopapi.shopapi.entity.Product;
import com.shopapi.shopapi.service.OrderService;
import com.shopapi.shopapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/{userId}")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    private final Set<Product> cart;


    public AdminController() {
        this.cart = new HashSet<>();
    }

    //User Requests
    @GetMapping("/products")
    public List<Product> findAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/cart")
    public Set<Product> getCart(){
        return cart;
    }

    @PostMapping("/cart/{productId}")
    public void addToCart(@PathVariable("productId") Long productId){
        cart.add(productService.findById(productId));
    }

    @DeleteMapping("/cart/{productId}")
    public void removeFromCart(@PathVariable("productId") Long id){
        cart.removeIf(s -> Objects.equals(s.getProductId(), id));
    }


    //Admin Requests

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product){
        productService.updateProduct(product);
    }

    @PatchMapping("/products/{productId}")
    public void updateProduct(@RequestBody Product product, @PathVariable("productId") Long productId){
        if(productService.existsById(productId)){
            product.setProductId(productId);
        }
        productService.updateProduct(product);
    }

    @DeleteMapping("/products/{productId}")
    public void removeProduct(@PathVariable("productId") Long id){
        productService.removeProductById(id);
    }

}
