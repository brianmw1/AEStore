package com.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
