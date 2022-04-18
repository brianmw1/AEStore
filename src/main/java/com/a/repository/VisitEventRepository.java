package com.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a.entity.Item;
import com.a.entity.VisitEvent;

public interface VisitEventRepository extends JpaRepository<VisitEvent, Integer> {

}
