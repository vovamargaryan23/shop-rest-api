package com.shopapi.shopapi.service;

import com.shopapi.shopapi.entity.Order;
import com.shopapi.shopapi.entity.OrderStatus;
import com.shopapi.shopapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }


    public void setStatusById(Long orderId, OrderStatus status){
        Optional<Order> newOrder = orderRepository.findById(orderId);
        newOrder.ifPresentOrElse((order -> {
            order.setOrderStatus(status);
            orderRepository.save(order);
        }),
                () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

    }

    public void save(Order order){
        orderRepository.save(order);
    }

}
