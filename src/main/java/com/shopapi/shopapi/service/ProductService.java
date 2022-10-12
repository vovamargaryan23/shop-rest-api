package com.shopapi.shopapi.service;

import com.shopapi.shopapi.entity.Product;
import com.shopapi.shopapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> updateProduct(Product product){
        return Optional.of(productRepository.save(product));
    }

    public void removeProductById(Long id){
        productRepository.deleteById(id);
    }
}
