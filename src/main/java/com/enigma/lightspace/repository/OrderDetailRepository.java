package com.enigma.lightspace.repository;

import com.enigma.lightspace.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String > {
}
