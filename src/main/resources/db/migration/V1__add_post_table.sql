CREATE TABLE post
(
    id      bigint PRIMARY KEY,
    title   varchar(255) NOT NULL,
    content text         NOT NULL,
    status  varchar(20)
);

ALTER TABLE post
    ADD CONSTRAINT uc_post_title UNIQUE (title);
