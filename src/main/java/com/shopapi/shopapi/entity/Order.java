package com.shopapi.shopapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(name = "user_id")
    @NonNull
    private Long userId;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    @NonNull
    private Product product;

    @Column(name = "order_date")
    @NonNull
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    @NonNull
    private OrderStatus orderStatus;
}
