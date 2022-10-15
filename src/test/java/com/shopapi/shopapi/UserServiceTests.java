package com.shopapi.shopapi;


import com.shopapi.shopapi.entity.Role;
import com.shopapi.shopapi.entity.User;
import com.shopapi.shopapi.repository.UserRepository;
import com.shopapi.shopapi.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private User admin;


    @DisplayName("JUnit test for findById method")
    @Test
    public void FindByIdMethodInUserServiceTest(){
        Mockito.when(userRepository.findById(10L)).thenReturn(Optional.of(new User(1L, "Test", "User", Role.ROLE_USER)));

        User user1 = userService.findById(10L);

        Assertions.assertEquals("Test",user1.getFirstName());
        Assertions.assertEquals("User",user1.getLastName());
        Assertions.assertEquals(Role.ROLE_USER,user1.getRole());
    }

}
