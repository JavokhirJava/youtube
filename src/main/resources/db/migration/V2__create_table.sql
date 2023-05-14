CREATE TABLE IF NOT EXISTS profile (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    attach_id VARCHAR(255),
    status VARCHAR(255),
    role VARCHAR(255),
    visible BOOLEAN,
    created_date TIMESTAMP,
    prt_id INTEGER,
    FOREIGN KEY (attach_id)  REFERENCES attach(id)
    );
