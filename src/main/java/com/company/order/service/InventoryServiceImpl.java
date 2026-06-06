package com.company.order.service;

import com.company.order.dto.InventoryResponse;
import com.company.order.dto.InventorySearchRequest;
import com.company.order.entity.Inventory;
import com.company.order.repository.InventoryRepository;
import com.company.order.specification.InventorySpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class InventoryServiceImpl
        implements InventoryService {

    private final InventoryRepository repository;

    @Override
    public List<InventoryResponse> search(InventorySearchRequest request) {

        log.info("Searching inventory {}", request);

        Specification<Inventory> spec =
                InventorySpecification.build(request);

        return repository.findAll(spec)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private InventoryResponse toResponse(
            Inventory inventory) {

        return new InventoryResponse(
                inventory.getId(),
                inventory.getName(),
                inventory.getCategory(),
                inventory.getPrice(),
                inventory.getStock());
    }
}
