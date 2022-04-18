package com.a.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a.entity.PurchaseOrderItem;
import com.a.entity.PurchaseOrderItemKey;

@Transactional
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, PurchaseOrderItemKey>{

}
