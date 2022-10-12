package com.shopapi.shopapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product_categories")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", nullable = false)
    private Long id;

}