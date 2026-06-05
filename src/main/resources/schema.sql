
-- Generic schema (H2 / embedded DB friendly) for ORDERS table generated from Order entity
-- This file is placed in src/main/resources so tests using H2 can initialize the schema.

CREATE TABLE IF NOT EXISTS orders (
  order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  customer_name VARCHAR(200) NOT NULL,
  amount DECIMAL(19,2) NOT NULL,
  status VARCHAR(50) NOT NULL,
  created_at TIMESTAMP NOT NULL
);

-- Index to match entity @Index
-- Note: some databases (including MySQL and H2) do not support "IF NOT EXISTS" with CREATE INDEX
-- Use a plain CREATE INDEX (it's safe during fresh schema creation) or manage index existence from migration tools.
CREATE INDEX idx_orders_status ON orders (status);

-- Sample data used in tests
INSERT INTO orders (customer_name, amount, status, created_at) VALUES
('John Doe', 1500.50, 'COMPLETED', TIMESTAMP '2026-06-05 10:15:30');



