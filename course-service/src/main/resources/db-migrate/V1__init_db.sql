CREATE TABLE IF NOT EXISTS course (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    startDate DATE,
    price  NUMERIC,
    noOfMember INTEGER
);