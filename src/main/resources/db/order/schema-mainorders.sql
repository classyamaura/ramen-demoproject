CREATE TABLE mainorders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    store_name VARCHAR(255),
    person_in_charge VARCHAR(255),
    order_date DATE,
    remarks TEXT
); 