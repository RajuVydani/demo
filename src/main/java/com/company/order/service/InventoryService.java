package com.company.order.service;

import com.company.order.dto.InventoryResponse;
import com.company.order.dto.InventorySearchRequest;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> search(
            InventorySearchRequest request);
}