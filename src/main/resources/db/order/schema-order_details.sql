CREATE TABLE order_details (
    order_detail_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    item_name VARCHAR(255),
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES mainorders(order_id)
); 