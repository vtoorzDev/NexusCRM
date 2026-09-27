CREATE TABLE contacts(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR (255) NOT NULL ,
    description VARCHAR (255) NOT NULL ,
    contact_Date DATE,
    contact_status VARCHAR (255),
    client_id BIGINT NOT NULL,

                    CONSTRAINT fk_contacts_client
                     FOREIGN KEY (client_id)
                     REFERENCES clients(id)
);