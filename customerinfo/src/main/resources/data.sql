DROP TABLE IF EXISTS customers;

CREATE TABLE customers (id INT AUTO_INCREMENT PRIMARY KEY, 
					   first_name VARCHAR(50) NOT NULL,
					   last_name VARCHAR(50) NOT NULL,
					   ssn VARCHAR(11) NOT NULL);
					   
INSERT INTO customers(first_name, last_name, ssn) VALUES
	('Fulano', 'de Tal', '201087-2020'),
	('Cicrano', 'de Tal', '141087-1010'),
	('Beltrano', 'de Tal', '041087-3030');
					   