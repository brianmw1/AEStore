package com.a.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a.entity.Item;

@Transactional
public interface ItemRepository extends JpaRepository<Item, String> {

}