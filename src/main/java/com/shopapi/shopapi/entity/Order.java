package com.shopapi.shopapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @NonNull
    private Long userId;

    @ManyToOne(targetEntity = Product.class)
    @NonNull
    @JoinColumn(name = "product_id")
    private Long productId;

    @Column(name = "order_date")
    @NonNull
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    @NonNull
    private OrderStatus orderStatus;
}
