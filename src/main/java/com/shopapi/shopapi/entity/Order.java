package com.shopapi.shopapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private Long userId;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Long productId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "order_date")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
}
