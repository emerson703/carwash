CREATE TABLE IF NOT EXISTS bookings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    vehicle_type VARCHAR(50) NOT NULL,
    placa VARCHAR(20) NOT NULL,
    booking_time TIMESTAMP NOT NULL,
    wash_type VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL
);