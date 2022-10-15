package com.shopapi.shopapi;

import com.shopapi.shopapi.entity.*;
import com.shopapi.shopapi.entity.Order;
import com.shopapi.shopapi.repository.OrderRepository;
import com.shopapi.shopapi.service.OrderService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;

    private final Product product = new Product(1L,"Test Product",999L, new ProductCategory(1L,"Test Category"),67L);


    @InjectMocks
    private OrderService orderService;

    @DisplayName("JUnit test for findAll method")
    @Test
    public void findAllMethodOrderServiceTest(){
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order(10L,14L,product, LocalDateTime.of(1,1,1,1,1),OrderStatus.PENDING);
        Order order2 = new Order(11L,15L,product, LocalDateTime.of(25,1,1,15,1),OrderStatus.APPROVED);
        Order order3 = new Order(12L,16L,product, LocalDateTime.of(25111,1,1,15,1),OrderStatus.DECLINED);

        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);

        Mockito.when(orderRepository.findAll()).thenReturn(orderList);

        List<Order> list = orderService.findAll();

        Assertions.assertEquals(3,list.size());
        Mockito.verify(orderRepository,Mockito.times(1)).findAll();
    }

    @DisplayName("JUnit test for setStatusById method")
    @Test
    public void setStatusByIdMethodOrderServiceTest(){
        Order order1 = new Order(10L,14L,product, LocalDateTime.of(1,1,1,1,1),OrderStatus.PENDING);

        Mockito.when(orderRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(order1));

        orderService.setStatusById(10L,OrderStatus.DECLINED);
        Assertions.assertEquals(OrderStatus.DECLINED,order1.getOrderStatus());

        orderService.setStatusById(10L,OrderStatus.APPROVED);
        Assertions.assertEquals(OrderStatus.APPROVED,order1.getOrderStatus());
    }

    @DisplayName("JUnit test for setStatusById method to throw exception")
    @Test
    public void setStatusByIdMethodExceptionTest(){
        Mockito.when(orderRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> orderService.setStatusById(23L,OrderStatus.DECLINED));
    }

}
