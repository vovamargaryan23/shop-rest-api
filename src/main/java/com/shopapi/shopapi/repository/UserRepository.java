package com.shopapi.shopapi.repository;

import com.shopapi.shopapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
