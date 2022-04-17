package com.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a.entity.PurchaseOrderItem;
import com.a.entity.PurchaseOrderItemKey;

public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, PurchaseOrderItemKey>{

}
