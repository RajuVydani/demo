

CREATE TABLE inventory (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,

                           name VARCHAR(255) NOT NULL,

                           category VARCHAR(100) NOT NULL,

                           subcategory VARCHAR(100),

                           manufacturing_date DATE,

                           expiry_date DATE,

                           specification TEXT,

                           price DECIMAL(12,2) NOT NULL,

                           stock INT NOT NULL DEFAULT 0,

                           model VARCHAR(100),

                           seller VARCHAR(255),

                           location VARCHAR(255),

                           brand VARCHAR(100),

                           sku VARCHAR(100) UNIQUE,

                           status VARCHAR(50) DEFAULT 'ACTIVE',

                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                               ON UPDATE CURRENT_TIMESTAMP,

                           INDEX idx_inventory_name (name),

                           INDEX idx_inventory_category (category),

                           INDEX idx_inventory_subcategory (subcategory),

                           INDEX idx_inventory_seller (seller),

                           INDEX idx_inventory_location (location),

                           INDEX idx_inventory_price (price),

                           INDEX idx_inventory_expiry_date (expiry_date)
);

INSERT INTO inventory (name, category, subcategory, manufacturing_date, expiry_date, specification, price, stock, model, seller, location, brand, sku, status) VALUES
                                                                                                                                                                   ('Organic Apples', 'Food', 'Fruit', '2026-05-01', '2026-06-01', 'Fresh organic red apples, 1kg bag', 3.99, 150, NULL, 'GreenFarm Co.', 'Warehouse A', 'GreenFarm', 'SKU-FOOD-0001', 'ACTIVE'),
                                                                                                                                                                   ('Whole Milk 1L', 'Food', 'Dairy', '2026-05-20', '2026-06-10', 'Pasteurized whole milk, 1 liter carton', 1.49, 320, NULL, 'DairyBest', 'Cold Storage 1', 'DairyBest', 'SKU-FOOD-0002', 'ACTIVE'),
                                                                                                                                                                   ('Laptop Pro 15', 'Electronics', 'Computers', '2025-11-10', NULL, '15-inch laptop, 16GB RAM, 512GB SSD', 1299.00, 25, 'LP15-2025', 'TechDistributors', 'Shelf-E3', 'NovaTech', 'SKU-EL-0001', 'ACTIVE'),
                                                                                                                                                                   ('USB-C Charger 65W', 'Electronics', 'Accessories', '2026-01-05', NULL, '65W USB-C PD charger with foldable plug', 29.95, 420, 'UC65-01', 'CableHouse', 'Shelf-A1', 'ChargeCo', 'SKU-EL-0002', 'ACTIVE'),
                                                                                                                                                                   ('Office Chair - Mesh', 'Furniture', 'Office', '2026-02-18', NULL, 'Ergonomic mesh office chair with adjustable height', 149.99, 80, 'OC-MESH-01', 'FurniCorp', 'Shelf-F2', 'FurniCorp', 'SKU-FUR-0001', 'ACTIVE'),
                                                                                                                                                                   ('LED Desk Lamp', 'Home', 'Lighting', '2026-03-30', NULL, 'Adjustable LED desk lamp with dimmer', 24.50, 210, 'LAMP-LED-10', 'BrightLights', 'Shelf-H5', 'BrightLights', 'SKU-HOME-0001', 'ACTIVE'),
                                                                                                                                                                   ('Canned Tuna 120g', 'Food', 'Canned', '2025-10-01', '2027-10-01', 'Premium tuna in olive oil, 120g can', 2.19, 500, NULL, 'OceanFoods', 'Warehouse B', 'OceanFoods', 'SKU-FOOD-0003', 'ACTIVE'),
                                                                                                                                                                   ('Hand Sanitizer 500ml', 'Health', 'Sanitizers', '2026-04-01', '2028-04-01', 'Alcohol-based hand sanitizer 70% - 500ml', 6.75, 640, NULL, 'SafeHands', 'Shelf-C3', 'SafeHands', 'SKU-HE-0001', 'ACTIVE'),
                                                                                                                                                                   ('Smartphone X', 'Electronics', 'Mobile', '2026-04-15', NULL, '6.7" OLED, 256GB, 12MP triple camera', 899.00, 40, 'SPX-256', 'MobileMart', 'Shelf-E1', 'Orbit', 'SKU-EL-0003', 'ACTIVE'),
                                                                                                                                                                   ('Running Shoes - Men', 'Clothing', 'Footwear', '2026-03-05', NULL, 'Breathable running shoes, size range available', 79.99, 120, 'RS-M-01', 'SportStore', 'Shelf-D4', 'Stride', 'SKU-CLO-0001', 'ACTIVE'),
                                                                                                                                                                   ('Disposable Gloves 100pc', 'Health', 'Gloves', '2026-01-20', '2028-01-20', 'Latex-free disposable gloves, 100 pieces', 8.99, 300, NULL, 'MediSupply', 'Warehouse C', 'MediSupply', 'SKU-HE-0002', 'ACTIVE'),
                                                                                                                                                                   ('Organic Yogurt 500g', 'Food', 'Dairy', '2026-05-25', '2026-06-20', 'Greek-style organic yogurt, plain', 4.49, 95, NULL, 'DairyBest', 'Cold Storage 1', 'DairyBest', 'SKU-FOOD-0004', 'ACTIVE'),
                                                                                                                                                                   ('4K Monitor 27"', 'Electronics', 'Monitors', '2025-12-01', NULL, '27 inch 4K IPS monitor, HDR', 349.95, 30, 'MON4K-27', 'DisplayWorld', 'Shelf-E4', 'ViewMax', 'SKU-EL-0004', 'ACTIVE'),
                                                                                                                                                                   ('Ceramic Mug 350ml', 'Home', 'Kitchenware', '2026-03-12', NULL, 'White ceramic mug with gloss finish', 7.50, 180, NULL, 'HomeEssentials', 'Shelf-H2', 'HomeEssentials', 'SKU-HOME-0002', 'ACTIVE'),
                                                                                                                                                                   ('Chocolate Bar 100g', 'Food', 'Confectionery', '2026-05-10', '2027-05-10', 'Dark chocolate 70% cocoa, 100g', 2.99, 1000, NULL, 'SweetCo', 'Shelf-B1', 'SweetCo', 'SKU-FOOD-0005', 'ACTIVE'),
                                                                                                                                                                   ('Air Conditioner 1.5T', 'Electronics', 'Appliances', '2024-06-15', NULL, 'Split AC 1.5 Ton, inverter technology', 599.00, 12, 'AC-1.5T-INV', 'CoolAir Ltd.', 'Warehouse D', 'CoolAir', 'SKU-EL-0005', 'ACTIVE'),
                                                                                                                                                                   ('Expired Sample - Yogurt', 'Food', 'Dairy', '2025-11-01', '2026-01-01', 'Expired test product', 0.50, 0, NULL, 'TestSeller', 'Quarantine', 'TestBrand', 'SKU-EX-0001', 'DISCONTINUED'),
                                                                                                                                                                   ('Replacement Filter', 'Home', 'Appliance Parts', '2026-02-01', NULL, 'Replacement HEPA filter for AC models', 39.00, 65, 'RF-HEPA-01', 'PartsHub', 'Shelf-H9', 'FilterPros', 'SKU-HOME-0003', 'ACTIVE'),
                                                                                                                                                                   ('Garden Trowel', 'Garden', 'Tools', '2026-03-01', NULL, 'Steel garden trowel with wooden handle', 12.99, 55, 'GT-100', 'GardenSupply', 'Shelf-G1', 'GardenPro', 'SKU-GRD-0001', 'ACTIVE'),
                                                                                                                                                                   ('Special Edition Watch', 'Accessories', 'Watches', '2023-08-10', NULL, 'Limited edition mechanical watch with leather strap', 2499.00, 2, 'SEW-2023', 'TimeKeepers', 'Shelf-A5', 'ChronoLux', 'SKU-ACC-0001', 'ACTIVE');

-- End of sample inserts