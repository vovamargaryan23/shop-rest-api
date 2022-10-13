package com.shopapi.shopapi.repository;

import com.shopapi.shopapi.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
    boolean existsByName(String name);

    ProductCategory findByName(String name);
}
