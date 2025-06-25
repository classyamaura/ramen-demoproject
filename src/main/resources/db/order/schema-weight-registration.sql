CREATE TABLE weight_sensors (
    sensor_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sensor_name VARCHAR(255) NOT NULL,
    material_name VARCHAR(255) NOT NULL,
    registered_weight INT,
    threshold_weight INT
); 