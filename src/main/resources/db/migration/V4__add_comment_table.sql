CREATE TABLE comment
(
    id         bigint PRIMARY KEY,
    body       varchar(200) NOT NULL,
    post_id    bigint       NOT NULL,
    user_id    bigint       NOT NULL,
    created_at timestamptz  NOT NULL
);

ALTER TABLE comment
    ADD CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES post (id),
    ADD CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES users (id);
