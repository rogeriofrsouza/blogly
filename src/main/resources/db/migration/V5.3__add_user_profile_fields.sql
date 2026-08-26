ALTER TABLE users
    ADD COLUMN bio        varchar(200),
    ADD COLUMN avatar_key varchar(100),
    ADD COLUMN joined_at  timestamptz NOT NULL DEFAULT NOW();

ALTER TABLE users
    ALTER COLUMN joined_at DROP DEFAULT;
