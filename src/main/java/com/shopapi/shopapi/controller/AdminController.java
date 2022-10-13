package com.shopapi.shopapi.controller;

import com.shopapi.shopapi.entity.Product;
import com.shopapi.shopapi.service.OrderService;
import com.shopapi.shopapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/products")
    public List<Product> findAllProducts(){
        return productService.findAll();
    }

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
