package com.shopapi.shopapi.controller;

import com.shopapi.shopapi.entity.Order;
import com.shopapi.shopapi.entity.OrderStatus;
import com.shopapi.shopapi.entity.Product;
import com.shopapi.shopapi.entity.Role;
import com.shopapi.shopapi.service.OrderService;
import com.shopapi.shopapi.service.ProductService;
import com.shopapi.shopapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@RestController
@RequestMapping("/{userId}")
public class MainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    private final Set<Product> cart;

    private final Set<Order> submission;


    public MainController() {
        this.cart = new HashSet<>();
        this.submission = new HashSet<>();
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

    @PostMapping("/cart/submit")
    public void submitForApproval(@PathVariable("userId") Long userId){
        cart.forEach(product -> {
            submission.add(new Order(userId,product.getProductId(), LocalDateTime.now(), OrderStatus.PENDING));
        });
        orderService.saveAll(submission);
        submission.clear();
        cart.clear();
    }


    //Admin Requests

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product, @PathVariable("userId") Long userId){
        checkUser(userId);
        productService.updateProduct(product);
    }

    @PatchMapping("/products/{productId}")
    public void updateProduct(@RequestBody Product product, @PathVariable("productId") Long productId, @PathVariable("userId") Long userId){
        checkUser(userId);
        if(productService.existsById(productId)){
            product.setProductId(productId);
        }
        productService.updateProduct(product);
    }

    @DeleteMapping("/products/{productId}")
    public void removeProduct(@PathVariable("productId") Long id,@PathVariable("userId") Long userId){
        checkUser(userId);
        productService.removeProductById(id);
    }

    @GetMapping("/orders")
    public List<Order> getOrders(@PathVariable("userId") Long userId){
        checkUser(userId);
       return orderService.findAll();
    }

    @PostMapping("/orders/{orderId}/approve")
    public void approveOrder(@PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId){
        checkUser(userId);
        orderService.setStatusById(orderId,OrderStatus.APPROVED);
    }

    @PostMapping("/orders/{orderId}/decline")
    public void declineOrder(@PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId){
        checkUser(userId);
        orderService.setStatusById(orderId,OrderStatus.DECLINED);
    }


    private void checkUser(Long id){
        if(!userService.findById(id).getRole().equals(Role.ROLE_ADMIN)){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

}
