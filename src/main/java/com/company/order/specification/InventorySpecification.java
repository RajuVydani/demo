package com.company.order.specification;

import com.company.order.dto.InventorySearchRequest;
import com.company.order.entity.Inventory;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

@UtilityClass
public class InventorySpecification {

    public static Specification<Inventory> build(
            InventorySearchRequest request) {

        return Specification.allOf(
                nameLike(request.name()),
                category(request.category()),
                subcategory(request.subcategory()),
                brand(request.brand()),
                model(request.model()),
                seller(request.seller()),
                location(request.location()),
                minPrice(request.minPrice()),
                maxPrice(request.maxPrice()),
                minStock(request.minStock()),
                manufacturingDate(request.manufacturingDate()),
                expiryDate(request.expiryDate())
        );
    }

    private static Specification<Inventory> nameLike(String name) {
        return (root, query, cb) ->
                name == null || name.isBlank()
                        ? null
                        : cb.like(
                        cb.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%");
    }

    private static Specification<Inventory> category(String category) {
        return (root, query, cb) ->
                category == null || category.isBlank()
                        ? null
                        : cb.equal(
                        cb.lower(root.get("category")),
                        category.toLowerCase());
    }

    private static Specification<Inventory> subcategory(String subcategory) {
        return (root, query, cb) ->
                subcategory == null || subcategory.isBlank()
                        ? null
                        : cb.equal(
                        cb.lower(root.get("subcategory")),
                        subcategory.toLowerCase());
    }

    private static Specification<Inventory> brand(String brand) {
        return (root, query, cb) ->
                brand == null || brand.isBlank()
                        ? null
                        : cb.equal(
                        cb.lower(root.get("brand")),
                        brand.toLowerCase());
    }

    private static Specification<Inventory> model(String model) {
        return (root, query, cb) ->
                model == null || model.isBlank()
                        ? null
                        : cb.equal(
                        cb.lower(root.get("model")),
                        model.toLowerCase());
    }

    private static Specification<Inventory> seller(String seller) {
        return (root, query, cb) ->
                seller == null || seller.isBlank()
                        ? null
                        : cb.equal(
                        cb.lower(root.get("seller")),
                        seller.toLowerCase());
    }

    private static Specification<Inventory> location(String location) {
        return (root, query, cb) ->
                location == null || location.isBlank()
                        ? null
                        : cb.equal(
                        cb.lower(root.get("location")),
                        location.toLowerCase());
    }

    private static Specification<Inventory> minPrice(
            BigDecimal minPrice) {

        return (root, query, cb) ->
                minPrice == null
                        ? null
                        : cb.greaterThanOrEqualTo(
                        root.get("price"),
                        minPrice);
    }

    private static Specification<Inventory> maxPrice(
            BigDecimal maxPrice) {

        return (root, query, cb) ->
                maxPrice == null
                        ? null
                        : cb.lessThanOrEqualTo(
                        root.get("price"),
                        maxPrice);
    }

    private static Specification<Inventory> minStock(
            Integer minStock) {

        return (root, query, cb) ->
                minStock == null
                        ? null
                        : cb.greaterThanOrEqualTo(
                        root.get("stock"),
                        minStock);
    }

    private static Specification<Inventory> manufacturingDate(
            LocalDate manufacturingDate) {

        return (root, query, cb) ->
                manufacturingDate == null
                        ? null
                        : cb.equal(
                        root.get("manufacturingDate"),
                        manufacturingDate);
    }

    private static Specification<Inventory> expiryDate(
            LocalDate expiryDate) {

        return (root, query, cb) ->
                expiryDate == null
                        ? null
                        : cb.equal(
                        root.get("expiryDate"),
                        expiryDate);
    }
}