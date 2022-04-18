package com.a.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a.entity.PurchaseOrder;

@Transactional
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}
