package com.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}
