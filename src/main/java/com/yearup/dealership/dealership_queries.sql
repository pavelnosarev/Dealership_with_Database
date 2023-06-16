-- get all dealerships

SELECT * FROM dealerships;


--  vehicles by dealership

SELECT vehicles.*
FROM vehicles
JOIN inventory ON vehicles.VIN = inventory.VIN
WHERE inventory.dealership_id = <dealership_id>;
-- replace dealership id in the <>

--find car by VIN number

SELECT *
FROM vehicles
WHERE VIN = '<vin>';
-- replace vin number in <>

-- find vehicle location by dealership

SELECT dealerships.*
FROM dealerships
JOIN inventory ON dealerships.dealership_id = inventory.dealership_id
WHERE inventory.VIN = '<vin>';
-- replace vin number in <>

--get sales info from certain dealer using date range

SELECT sales_contracts.*, vehicles.*, dealerships.name AS dealership_name
FROM sales_contracts
JOIN vehicles ON sales_contracts.VIN = vehicles.VIN
JOIN inventory ON vehicles.VIN = inventory.VIN
JOIN dealerships ON inventory.dealership_id = dealerships.dealership_id
WHERE dealerships.dealership_id = <dealer_id>
  AND sales_contracts.sale_date BETWEEN '2023-01-01' AND '<end_date>';
--replace  dealer_id and end_date
