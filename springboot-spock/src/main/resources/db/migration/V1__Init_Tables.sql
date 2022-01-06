CREATE TABLE IF NOT EXISTS user (
    `id` bigint not null unique AUTO_INCREMENT,
    `name` varchar(250) not null
);

INSERT INTO user(name) VALUES ('Pin');
