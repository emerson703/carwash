-- Insertar clientes de prueba
INSERT INTO customer (name, last_name, dni, phone, address) VALUES
('Juan', 'Perez', '12345678', '999888777', 'Av. Siempre Viva 123'),
('Maria', 'Lopez', '87654321', '999111222', 'Calle Falsa 123'),
('Carlos', 'Gomez', '11223344', '999333444',  'Jr. Lima 456');

-- Insertar vehículos de prueba
INSERT INTO vehicle (placa, brand, model, type, id_customer) VALUES
('ABC123', 'Toyota', 'Corolla', 'Sedan', 1),
('XYZ789', 'Honda', 'Civic', 'Sedan', 1),
('DEF456', 'Nissan', 'Sentra', 'Sedan', 2),
('GHI789', 'Hyundai', 'Tucson', 'SUV', 3);

-- Insertar servicios de prueba
INSERT INTO service (name, description, price) VALUES
('Lavado Básico', 'Lavado exterior e interior básico', 25.00),
('Lavado Premium', 'Lavado completo con encerado', 45.00),
('Lavado Motor', 'Limpieza profunda del motor', 35.00);

INSERT INTO employee (name, last_name, dni, phone, status) VALUES
('Luis', 'Martinez', '55667788', '999555666', 'ACTIVE'),
('Ana', 'Rodriguez', '99887766', '999444333', 'ACTIVE');