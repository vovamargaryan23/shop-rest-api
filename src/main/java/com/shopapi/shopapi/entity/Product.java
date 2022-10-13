package com.shopapi.shopapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
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

    @OneToOne(targetEntity = ProductCategory.class)
    @JoinColumn(name = "category_id")
    @NonNull
    private ProductCategory productCategory;

    @Column(name = "count")
    @NonNull
    private Long count;
}
