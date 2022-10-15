package com.shopapi.shopapi.service;

import com.shopapi.shopapi.entity.Product;
import com.shopapi.shopapi.repository.CategoryRepository;
import com.shopapi.shopapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public void updateProduct(Product product){
        if(!categoryRepository.existsByName(product.getProductCategory().getName())){
            categoryRepository.save(product.getProductCategory());
        }
        else{
            product.setProductCategory(categoryRepository.findByName(product.getProductCategory().getName()));
        }
        productRepository.save(product);

    }

    public boolean existsById(Long id){
        return productRepository.existsById(id);
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void removeProductById(Long id){
        productRepository.findById(id).ifPresentOrElse((product -> {
            productRepository.deleteById(id);
        }),() ->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }
}
