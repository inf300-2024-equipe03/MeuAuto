CREATE TABLE addresses
(
    id          UUID                         NOT NULL,
    description VARCHAR(255)                 NOT NULL,
    postal_code VARCHAR(255)                 NOT NULL,
    google_id   VARCHAR(255)                 NOT NULL,
    location    BYTEA       NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);