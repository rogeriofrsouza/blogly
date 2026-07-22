ALTER TABLE post
    ADD COLUMN user_id bigint NOT NULL;

ALTER TABLE post
    ADD CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES users (id);
