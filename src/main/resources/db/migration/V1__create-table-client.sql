CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE client (
    id UUID PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    income NUMERIC(12,2) NOT NULL,
    birth_date TIMESTAMP NOT NULL,
    children INTEGER
);