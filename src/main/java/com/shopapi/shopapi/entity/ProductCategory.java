package com.shopapi.shopapi.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_categories")
@Getter
@Setter
@RequiredArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Column(name = "category_name",nullable = false)
    @NonNull
    private String name;

}