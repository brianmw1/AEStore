package com.a.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a.entity.User;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {

}
