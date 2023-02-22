package com.fernando.sqsordervalidator.repository;

import com.fernando.sqsordervalidator.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}