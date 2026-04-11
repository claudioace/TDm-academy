CREATE DATABASE IF NOT EXISTS academy_app;
USE academy_app;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

INSERT INTO users (dni, email, first_name, last_name, password, role) 
VALUES ('00000000', 'admin@academy.com', 'Admin', 'Admin', 
        '$2a$12$21ksFRPXYUEUvPEAdfeoQeTI8kOfY9w8o3SW.j0OPMTLgWn2au/5a', 
        'ADMIN')         
ON DUPLICATE KEY UPDATE email = email;