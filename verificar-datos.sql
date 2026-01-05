-- Script para verificar y crear tipos de prenda
-- Ejecutar en PostgreSQL

-- 1. Verificar tipos de prenda existentes
SELECT * FROM tipo_prenda ORDER BY id;

-- 2. Si no existen, crearlos (descomentar si es necesario)
-- Primero, reiniciar la secuencia si está vacía
-- ALTER SEQUENCE tipo_prenda_id_seq RESTART WITH 1;

-- Insertar tipos de prenda
INSERT INTO tipo_prenda (nombre, descripcion)
VALUES
    ('Camiseta', 'Camisetas'),
    ('Gorra', 'Gorras'),
    ('Pantalon', 'Pantalones'),
    ('Hoodie', 'Hoodies')
ON CONFLICT DO NOTHING;

-- 3. Verificar prendas existentes
SELECT p.id, p.nombre, p.talla, p.precio, p.stock, tp.descripcion as tipo
FROM prendas p
JOIN tipo_prenda tp ON p.tipo_id = tp.id
ORDER BY tp.id, p.nombre;

-- 4. Ejemplo de inserción de prendas (descomentar si necesitas datos de prueba)
/*
INSERT INTO prendas (nombre, talla, precio, stock, tipo_id, imagen_url)
VALUES
    -- Camisetas
    ('Camiseta Classic', 'M', 25.99, 15, 1, 'https://via.placeholder.com/150'),
    ('Camiseta Sport', 'L', 29.99, 10, 1, 'https://via.placeholder.com/150'),

    -- Gorras
    ('Gorra Baseball', 'Única', 19.99, 20, 2, 'https://via.placeholder.com/150'),
    ('Gorra Trucker', 'Única', 22.99, 15, 2, 'https://via.placeholder.com/150'),

    -- Pantalones
    ('Pantalón Deportivo', 'L', 45.99, 8, 3, 'https://via.placeholder.com/150'),
    ('Pantalón Jean', 'M', 55.99, 12, 3, 'https://via.placeholder.com/150'),

    -- Hoodies
    ('Hoodie Classic', 'M', 49.99, 10, 4, 'https://via.placeholder.com/150'),
    ('Hoodie Premium', 'L', 65.99, 5, 4, 'https://via.placeholder.com/150');
*/

