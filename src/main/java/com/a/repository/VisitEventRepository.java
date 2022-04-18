package com.a.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a.entity.Item;
import com.a.entity.VisitEvent;

@Transactional
public interface VisitEventRepository extends JpaRepository<VisitEvent, Integer> {

}
