CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    fullName VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS role_account (
    role_id INT NOT NULL,
    account_id INT NOT NULL,
    PRIMARY KEY (role_id, account_id),
    constraint fk_role
    foreign key (role_id) references role(id),
        constraint fk_account
    foreign key (account_id) references account(id)
);

INSERT INTO oauth_client_details
    (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
    VALUES ('clientIdPassword', '', '$2a$10$1VWszmAXLL4ZpX1w35S78uZivpJsR3WhZPkKczAxahbl6JewNIV8K', 'read', 'password,authorization_code,refresh_token', 'http://localhost:8080/login', '', null, null, '{}', ''),
           ('sampleClientId', '', null, 'read', 'implicit', '', '', null, null, '{}', 'read');

INSERT INTO role (name) VALUES ('USER'), ('ADMIN');

INSERT INTO account (username, password, fullName) VALUES ('tuan', '$2a$10$.V3.KZZNFjEEF69CaB32Wu49yPboiqmneC2xaUbMrC3M9SqMd9wbi', 'Nguyen Anh Tuan');

INSERT INTO role_account(role_id, account_id) VALUES (1, 1);