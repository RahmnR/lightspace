package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order,String>, JpaSpecificationExecutor<Order> {
}
