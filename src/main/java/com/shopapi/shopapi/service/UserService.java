package com.shopapi.shopapi.service;

import com.shopapi.shopapi.entity.Role;
import com.shopapi.shopapi.entity.User;
import com.shopapi.shopapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void addUsers(){
        userRepository.save(new User("User","Useryan", Role.ROLE_USER));
        userRepository.save(new User("Admin","Adminyan", Role.ROLE_ADMIN));
    }
}
