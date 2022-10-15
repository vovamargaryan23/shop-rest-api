package com.shopapi.shopapi;


import com.shopapi.shopapi.entity.Product;
import com.shopapi.shopapi.entity.ProductCategory;
import com.shopapi.shopapi.repository.ProductRepository;
import com.shopapi.shopapi.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @DisplayName("JUnit test for findAll method")
    @Test
    public void findAllMethodProductServiceTest(){
        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1L,"Test Product 1",999L,new ProductCategory(1L, "Test Category"),43L);
        Product product2 = new Product(2L,"Test Product 2",9919L,new ProductCategory(2L, "Test Category"),413L);
        Product product3 = new Product(3L,"Test Product 3",3999L,new ProductCategory(3L, "Test Category"),4333L);

        products.add(product1);
        products.add(product2);
        products.add(product3);

        Mockito.when(productRepository.findAll()).thenReturn(products);

        List<Product> list = productService.findAll();

        Assertions.assertEquals(3,list.size());
        Mockito.verify(productRepository,Mockito.times(1)).findAll();
    }

    @DisplayName("JUnit test for updateProduct method")
    @Test
    public void updateProductMethodProductServiceTest(){
        Product product = new Product(1L,"Test Product",999L,new ProductCategory(1L, "Test Category"),43L);

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Assertions.assertEquals(1L,product.getProductId());
        Assertions.assertEquals("Test Product",product.getName());
        Assertions.assertEquals(999L,product.getPrice());
        Assertions.assertEquals("Test Category",product.getProductCategory().getName());
        Assertions.assertEquals(43L,product.getCount());
    }

    @DisplayName("JUnit test for existsById method")
    @Test
    public void existsByIdMethodProductServiceTest(){
        Product product = new Product(1L,"Test Product",999L,new ProductCategory(1L, "Test Category"),43L);

        Mockito.when(productRepository.existsById(product.getProductId())).thenReturn(true);

        Assertions.assertTrue(productService.existsById(1L));
        Assertions.assertFalse(productService.existsById(2L));
    }

    @DisplayName("JUnit test for findById method")
    @Test
    public void findByIdMethodProductServiceTest(){
        Product product = new Product(1L,"Test Product",999L,new ProductCategory(1L, "Test Category"),43L);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Assertions.assertEquals(product,productService.findById(1L));
        Assertions.assertThrows(ResponseStatusException.class,() -> productService.findById(10L));
    }

    @DisplayName("JUnit test for removeProductById method")
    @Test
    public void removeProductByIdMethodProductServiceTest(){
        Product product = new Product(2L,"Test Product",777L,new ProductCategory(2L, "Test Category 2"),14L);

        Mockito.when(productRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(product));

        productService.removeProductById(2L);

        Mockito.verify(productRepository).deleteById(2L);
    }
}
