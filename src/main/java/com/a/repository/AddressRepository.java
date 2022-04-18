package com.a.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a.entity.Address;

@Transactional
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
