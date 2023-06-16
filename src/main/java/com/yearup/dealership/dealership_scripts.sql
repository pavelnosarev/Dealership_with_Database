DROP TABLE IF EXISTS dealerships;
DROP TABLE IF EXISTS vehicles;
DROP TABLE IF EXISTS inventory;
DROP TABLE IF EXISTS sales_contracts;
DROP TABLE IF EXISTS lease_contracts;

-- create tables for dealerships, vehicles, inventory,
--(cont.) sales_contracts, lease_contracts

CREATE TABLE dealerships (
  dealership_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  address VARCHAR(50),
  phone VARCHAR(12)
);

CREATE TABLE vehicles (
  VIN VARCHAR(17) NOT NULL PRIMARY KEY,
  SOLD BOOLEAN DEFAULT FALSE,
  color VARCHAR(20),
  vehicle_type VARCHAR(20)
);

CREATE TABLE inventory (
  dealership_id INT,
  VIN VARCHAR(17),
  PRIMARY KEY (dealership_id, VIN),
  FOREIGN KEY (dealership_id) REFERENCES dealerships (dealership_id),
  FOREIGN KEY (VIN) REFERENCES vehicles (VIN)
);

CREATE TABLE sales_contracts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  VIN VARCHAR(17),
  sale_date DATE,
  FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

CREATE TABLE lease_contracts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  VIN VARCHAR(17),
  FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

-- populate with sample data

INSERT INTO dealerships (name, address, phone)
VALUES
  ('ABC Motors', '123 Main St', '555-1234'),
  ('XYZ Auto', '456 Elm St', '555-5678'),
  ('123 Car Sales', '789 Oak St', '555-9012'),
  ('Best Wheels', '321 Pine St', '555-3456'),
  ('Sunset Motors', '987 Maple St', '555-7890');

INSERT INTO vehicles (VIN, SOLD, color, vehicle_type)
VALUES
  ('1HGCM82633A123456', FALSE, 'Blue', 'Sedan'),
  ('2G1WC5E30F1234567', TRUE, 'Red', 'Coupe'),
  ('3VWCM7AJ9DM123456', FALSE, 'Silver', 'Hatchback'),
  ('4S3BMHB68B1234567', FALSE, 'Black', 'SUV'),
  ('5YJSA1DN1CF123456', TRUE, 'White', 'Electric'),
  ('6F08C362589123456', TRUE, 'Yellow', 'Convertible'),
  ('7F03C123456123456', FALSE, 'Green', 'Pickup');

INSERT INTO inventory (dealership_id, VIN)
VALUES
  (1, '1HGCM82633A123456'),
  (1, '2G1WC5E30F1234567'),
  (2, '3VWCM7AJ9DM123456'),
  (3, '4S3BMHB68B1234567'),
  (4, '5YJSA1DN1CF123456'),
  (4, '6F08C362589123456'),
  (5, '7F03C123456123456');

INSERT INTO sales_contracts (VIN, sale_date)
VALUES
  ('2G1WC5E30F1234567', '2023-01-15'),
  ('5YJSA1DN1CF123456', '2023-02-10'),
  ('6F08C362589123456', '2023-02-28'),
  ('7F03C123456123456', '2023-03-05'),
  ('1HGCM82633A123456', '2023-04-02'),
  ('4S3BMHB68B1234567', '2023-04-20'),
  ('3VWCM7AJ9DM123456', '2023-05-08');

INSERT INTO lease_contracts (VIN)
VALUES
  ('3VWCM7AJ9DM123456'),
  ('1HGCM82633A123456'),
  ('4S3BMHB68B1234567'),
  ('2G1WC5E30F1234567'),
  ('7F03C123456123456'),
  ('5YJSA1DN1CF123456'),
  ('6F08C362589123456');
