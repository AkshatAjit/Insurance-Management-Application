CREATE DATABASE mydatabase;

USE mydatabase;

CREATE TABLE client (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE insurance_policy (
    id BIGINT NOT NULL AUTO_INCREMENT,
    policy_number VARCHAR(50) NOT NULL,
    policy_type VARCHAR(50) NOT NULL,
    coverage_amount DECIMAL(10, 2) NOT NULL,
    premium DECIMAL(10, 2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    client_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE claim (
    id BIGINT NOT NULL AUTO_INCREMENT,
    claim_number VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    claim_date DATE NOT NULL,
    claim_status VARCHAR(50) NOT NULL,
    insurance_policy_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (insurance_policy_id) REFERENCES insurance_policy(id)
);
