CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE novels (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        author_id INT,
                        CONSTRAINT fk_author
                            FOREIGN KEY (author_id)
                                REFERENCES authors(id)
                                ON DELETE SET NULL -- If an author is deleted, the author_id will be set to NULL
);