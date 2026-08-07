ALTER TABLE comment
    ADD COLUMN updated_at timestamptz;

UPDATE comment
SET updated_at = created_at;

ALTER TABLE comment
    ALTER COLUMN updated_at SET NOT NULL;
