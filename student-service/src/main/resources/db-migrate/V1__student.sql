CREATE TABLE IF NOT EXISTS student (

    id SERIAL PRIMARY KEY,

    name VARCHAR(255),

    dateOfBirth TIMESTAMP,

    hometown VARCHAR(255)
);