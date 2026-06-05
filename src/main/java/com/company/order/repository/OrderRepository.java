package com.company.order.repository;

import com.company.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Order entity.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    Optional<Order> findById(Long aLong);

}

