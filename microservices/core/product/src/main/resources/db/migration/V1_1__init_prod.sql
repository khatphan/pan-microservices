CREATE TABLE IF NOT EXISTS product
(id INT NOT NULL CONSTRAINT id_product_pk PRIMARY KEY,
name VARCHAR(255),
weight INT
);

--INSERT INTO product (id, name, weight) VALUES (1, 'Pan', 63);