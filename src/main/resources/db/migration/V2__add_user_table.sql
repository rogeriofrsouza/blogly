CREATE TABLE users
(
    id       bigint PRIMARY KEY,
    email    varchar(50)  NOT NULL UNIQUE,
    password varchar(150) NOT NULL,
    name     varchar(100) NOT NULL
);
