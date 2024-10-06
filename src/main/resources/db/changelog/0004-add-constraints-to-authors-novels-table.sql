ALTER TABLE authors
    ADD CONSTRAINT fk_authors_created_by
        FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL,
    ADD CONSTRAINT fk_authors_updated_by
        FOREIGN KEY (updated_by) REFERENCES users(id) ON DELETE SET NULL;

ALTER TABLE novels
    ADD CONSTRAINT fk_novels_created_by
        FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL,
    ADD CONSTRAINT fk_novels_updated_by
        FOREIGN KEY (updated_by) REFERENCES users(id) ON DELETE SET NULL;