CREATE TABLE clients(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (255) NOT NULL ,
    email VARCHAR (255) NOT NULL ,
    phone VARCHAR (255),
    company_client VARCHAR (255) NOT NULL,
    registration_date DATETIME,
    client_status VARCHAR (255) NOT NULL
);