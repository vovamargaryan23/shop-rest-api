package com.shopapi.shopapi;

import com.shopapi.shopapi.entity.Order;
import com.shopapi.shopapi.entity.Role;
import com.shopapi.shopapi.entity.User;
import com.shopapi.shopapi.repository.OrderRepository;
import com.shopapi.shopapi.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @DisplayName("JUnit test for findAll method")
    @Test
    public void findAllMethodOrderServiceTest(){
        List<Order> orderList = new ArrayList<>();

    }
}
