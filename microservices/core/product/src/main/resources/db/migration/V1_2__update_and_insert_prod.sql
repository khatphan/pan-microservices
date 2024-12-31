--ALTER TABLE Product
--ALTER COLUMN id TYPE INT SERIAL CONSTRAINT id_product_pk PRIMARY KEY;

INSERT INTO Product
(id, name, weight)
VALUES
(2, 'Leo', 63),
(3, 'Karaken', 88);