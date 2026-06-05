
-- MySQL schema for ORDERS table generated from Order entity
-- Run this against a MySQL database (e.g. orders_db)

CREATE DATABASE IF NOT EXISTS `orders_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `orders_db`;

CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `customer_name` VARCHAR(200) NOT NULL,
  `amount` DECIMAL(19,2) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `created_at` DATETIME NOT NULL,
  INDEX `idx_orders_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Example row
INSERT INTO `orders` (`customer_name`, `amount`, `status`, `created_at`) VALUES
('John Doe', 1500.50, 'COMPLETED', '2026-06-05 10:15:30');

