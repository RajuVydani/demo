# Inventory Service (demo)

This project contains a small Inventory search API implemented with Spring Boot. 
The service exposes a single, powerful search endpoint that lets clients filter inventory 
items by multiple criteria (name, category, price range, stock, dates, etc.).

---

## Architecture

The application follows a layered architecture
Constructor-based dependency injection is used for better testability and immutability.
Database design - Indexes have been added on commonly searched columns to improve search performance like on name, category.etc.

## Overview

The Inventory API provides a read-only search endpoint:

- GET `/api/v1/inventories`

It accepts multiple optional query parameters and returns a JSON array of `InventoryResponse` objects. The search is implemented using JPA Specifications so multiple filters may be combined. Matching on the `name` field is case-insensitive and supports partial matches.

Core model (response): `InventoryResponse`
- `id` (Long)
- `name` (String)
- `category` (String)
- `price` (BigDecimal)
- `stock` (Integer)

Source objects and filters are defined by `InventorySearchRequest` and `InventorySpecification`.

---

## Endpoint: GET /api/v1/inventories

Supported query parameters (all optional):

- `name` — partial, case-insensitive match against `name` (String)
- `category` — exact, case-insensitive match (String)
- `subcategory` — exact, case-insensitive match (String)
- `seller` — exact, case-insensitive match (String)
- `location` — exact, case-insensitive match (String)
- `brand` — exact, case-insensitive match (String)
- `model` — exact, case-insensitive match (String)
- `minPrice` — minimum price (decimal, >= 0)
- `maxPrice` — maximum price (decimal, >= 0)
- `minStock` — minimum stock (integer, >= 0)
- `manufacturingDate` — manufacturing date (ISO yyyy-MM-dd). Must be past or present.
- `expiryDate` — expiry date (ISO yyyy-MM-dd). Must be future.

Validation and behavior
- `minPrice` and `maxPrice` must be >= 0 (validated). If both are provided, `minPrice` must be <= `maxPrice`.
- `minStock` must be >= 0.
- `manufacturingDate` (if provided) must be in the past or present.
- `expiryDate` (if provided) must be in the future.
- If both `manufacturingDate` and `expiryDate` are provided, `manufacturingDate` must be on or before `expiryDate`.

The controller will return HTTP 400 (Bad Request) with a helpful message when these validation rules are violated.

### Assumptions

All search parameters are optional. If no search parameters are provided, all inventory records are returned.
When multiple search parameters are provided, they are combined using AND conditions as specified in the requirements.
String-based searches (name, category, subcategory, brand, seller, model, location) are treated as case-insensitive.
Name search supports partial matching using a contains search pattern.
Pagination and sorting are not implemented unless explicitly required by the use case.
Authentication and authorization are outside the scope of this assignment.
Audit fields such as createdBy and updatedBy are not maintained as user management requirements were not provided.
Data validation is performed at the API layer before executing database queries.
Database indexes have been added on commonly searched fields to improve query performance.
cache can be implemented to enhance performance for frequently accessed data.
Idenpotency is not a concern for this read-only search API, so no special handling is implemented for duplicate requests.
mapstruct is used for mapping between entity and response DTOs to keep the code clean and maintainable.











