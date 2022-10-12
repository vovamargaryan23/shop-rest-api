package com.shopapi.shopapi.repository;

import com.shopapi.shopapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Long, Order> {
}
