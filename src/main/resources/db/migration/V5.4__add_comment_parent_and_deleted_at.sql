ALTER TABLE comment
    ADD COLUMN parent_id  bigint,
    ADD COLUMN deleted_at timestamptz;

ALTER TABLE comment
    ADD CONSTRAINT fk_comment_parent FOREIGN KEY (parent_id) REFERENCES comment (id);

CREATE INDEX idx_comment_parent_id ON comment (parent_id);
