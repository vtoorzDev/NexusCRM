CREATE TABLE activities (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     title VARCHAR(255) NOT NULL,
     description VARCHAR(255) NOT NULL,
     due_date DATE NOT NULL,
     activity_status VARCHAR(255) NOT NULL,
     client_id BIGINT NOT NULL,
     attendant_id BIGINT NOT NULL,

                CONSTRAINT fk_activities_client
                FOREIGN KEY (client_id)
                REFERENCES clients(id),

                CONSTRAINT fk_activities_attendant
                FOREIGN KEY (attendant_id)
                REFERENCES attendants(id)
);