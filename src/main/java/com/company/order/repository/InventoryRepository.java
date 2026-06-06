package com.company.order.repository;

import com.company.order.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InventoryRepository
        extends JpaRepository<Inventory, Long>,
        JpaSpecificationExecutor<Inventory> {
}
