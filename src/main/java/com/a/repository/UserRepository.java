package com.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
