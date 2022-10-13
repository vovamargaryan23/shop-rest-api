package com.shopapi.shopapi.service;

import com.shopapi.shopapi.entity.Product;
import com.shopapi.shopapi.repository.CategoryRepository;
import com.shopapi.shopapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public void removeProductById(Long id){
        productRepository.deleteById(id);
    }
}
