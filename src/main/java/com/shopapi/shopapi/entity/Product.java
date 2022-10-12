package com.shopapi.shopapi.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "price")
    @NonNull
    private Long price;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    @NonNull
    private ProductCategory productCategory;

    @Column(name = "count")
    @NonNull
    private Long count;
}
