DROP TABLE IF EXISTS customers;

CREATE TABLE customers (id INT AUTO_INCREMENT PRIMARY KEY, 
					   first_name VARCHAR(50) NOT NULL,
					   last_name VARCHAR(50) NOT NULL,
					   ssn VARCHAR(11) NOT NULL,
					   birth_date DATE);
					   
INSERT INTO customers(first_name, last_name, ssn, birth_date) VALUES
	('Fulano', 'de Tal', '201087-2020', to_date('20-10-1987', 'DD-MM-RRRR')),
	('Cicrano', 'de Tal', '141087-1010', to_date('14-10-1987', 'DD-MM-RRRR')),
	('Beltrano', 'de Tal', '041087-3030', to_date('04-10-1987', 'DD-MM-RRRR'));
					   