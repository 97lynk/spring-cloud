CREATE TABLE IF NOT EXISTS tenant
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255),
    code     VARCHAR(15) unique,
    schema VARCHAR(15) unique,
    address  varchar(255)
);

INSERT INTO tenant (name, code, schema, address)
VALUES ('Trường Đại học Sư phạm Kỹ thuật TPHCM', 'hcmute', 'ute', '1 Võ Văn Ngân, P. Linh Chiểu, Q. Thủ Đức, TP.HCM'),
       ('Trường Đại học Công nghệ TPHCM', 'hutech', 'hutech', '475A Điện Biên Phủ, P.25, Q. Bình Thạnh, TP.HCM');
