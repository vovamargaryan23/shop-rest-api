package com.shopapi.shopapi.repository;

import com.shopapi.shopapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Long, Product> {
}
