package com.shopapi.shopapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_categories")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "category_name",nullable = false, unique = true)
    @NonNull
    private String name;

}